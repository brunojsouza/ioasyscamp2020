package br.com.brunojsouza.ioasyscamp2020

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btBuscar.setOnClickListener {
            consultCEP(edCep.text.toString())
        }
    }

    private fun consultCEP(cep: String) {
        Service.retrofit.getConsultCEP(cep).enqueue(object : Callback<CEPResponse> {
            override fun onFailure(call: Call<CEPResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "ERRO: $t", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<CEPResponse>, response: Response<CEPResponse>) {
                response.body()?.let {
                    tvAddress.text = String.format("Rua: %s\nBairro: %s\nCidade: %s", it.logradouro, it.bairro, it.localidade )

                }
            }
        })
    }
}
