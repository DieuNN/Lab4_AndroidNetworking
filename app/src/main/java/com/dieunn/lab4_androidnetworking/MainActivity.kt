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
            if (binding.txtId.text.isNullOrBlank()) {
                Toast.makeText(this, "Check your Id!", Toast.LENGTH_SHORT).show()
            } else {
                getDataWithId(binding.txtId.text.toString())
            }

        }

        binding.btnDelete.setOnClickListener {
            if (binding.txtId.text.isNullOrBlank()) {
                Toast.makeText(this, "Check your id!", Toast.LENGTH_SHORT).show()
            } else {
                deleteDataWithId(binding.txtId.text.toString())
            }
        }

        binding.btnUpdate.setOnClickListener {
            if (binding.txtId.text.isNullOrBlank() ||
                binding.txtFirstName.text.isNullOrBlank() ||
                binding.txtLastName.text.isNullOrBlank() ||
                binding.txtEmail.text.isNullOrBlank()
            ) {
                Toast.makeText(this, "Check your input!", Toast.LENGTH_SHORT).show()
            } else {
                updateDataWithId(
                    guestId =  binding.txtId.text.toString(),
                    firstName = binding.txtFirstName.text.toString(),
                    lastName = binding.txtLastName.text.toString(),
                    email = binding.txtEmail.text.toString()
                )
            }
        }


    }

    private fun getDataWithId(guestId: String) {
        val result = ApiInstance.api.getGuestWithId(guest_id = guestId)
        result.enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                binding.txtResult.text = response.body().toString()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                binding.txtResult.text = t.message.toString()
            }
        })


    }

    private fun deleteDataWithId(guestId: String) {
        val result = ApiInstance.api.deleteGuest(guest_id = guestId)
        result.enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                binding.txtResult.text = response.body().toString()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                binding.txtResult.text = t.message.toString()
            }
        })
    }

    private fun updateDataWithId(
        guestId: String,
        firstName: String,
        lastName: String,
        email: String
    ) {
        val result = ApiInstance.api.updateGuest(guest_id = guestId, firstName, lastName, email)
        result.enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                binding.txtResult.text = response.body().toString()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                binding.txtResult.text = t.message.toString()
            }
        })
    }


//    private fun deleteGuestWithId

//    @OptIn(DelicateCoroutinesApi::class)
//    private fun getData() {
//        GlobalScope.launch(Dispatchers.Main) {
//            val guestList = ApiInstance.api.getGuestList()
//            val stringBuilder = StringBuilder()
//
//            for (element in guestList) {
//                stringBuilder.append("First name: ").append(element.firstName).append(" ,")
//                    .append("last name: ").append(element.lastName)
//                    .append(" ,").append("email: ").append(element.email).append("\n")
//            }
//            binding.txtResult.text = stringBuilder.toString();
//        }
//    }


}