package com.dieunn.lab4_androidnetworking

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dieunn.lab4_androidnetworking.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGet.setOnClickListener {
            getData()
        }

        binding.btnPost.setOnClickListener {
            if (binding.txtFirstName.text.isNullOrBlank() || binding.txtLastName.text.isNullOrBlank() || binding.txtEmail.text.isNullOrBlank()) {
                Toast.makeText(this, "Check your input", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            addNewGuest(
                firstName = binding.txtFirstName.text.toString(),
                lastName = binding.txtFirstName.text.toString(),
                email = binding.txtEmail.text.toString()
            )
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getData() {
        GlobalScope.launch(Dispatchers.Main) {
            val guestList = ApiInstance.api.getGuestList()
            val stringBuilder = StringBuilder()

            for (element in guestList) {
                stringBuilder.append("First name: ").append(element.firstName).append(" ,")
                    .append("last name: ").append(element.lastName)
                    .append(" ,").append("email: ").append(element.email).append("\n")
            }
            binding.txtResult.text = stringBuilder.toString();
        }
    }

    private fun addNewGuest(firstName: String, lastName: String, email: String) {

            val result = ApiInstance.api.addNewGuest(firstName, lastName, email)
            result.enqueue( object : retrofit2.Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    try {
                        Toast.makeText(this@MainActivity, "Posted", Toast.LENGTH_SHORT).show()
                        binding.txtResult.text = response.body().toString()
                    } catch (e:Exception) {
                        Log.d(TAG, "onResponse: $e")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "onResponse: $t")
                }
            })

    }


}