package br.com.brunojsouza.ioasyscamp2020.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.brunojsouza.ioasyscamp2020.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btBuscar.setOnClickListener {
            viewModel.consultCEP(edCep.text.toString())
        }
        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.cepResponse.observe(this, Observer { endereco ->
//            Primeira forma de formatação de texto
            tvAddress.text = String.format(
                "Rua: %s\nBairro: %s\nCidade: %s",
                endereco.logradouro,
                endereco.bairro,
                endereco.localidade
            )
//            segunga forma de formatação de texto
//            tvAddress.text =
//                "Rua: ${endereco.logradouro}\nBairro: ${endereco.bairro}\nCidade: ${endereco.localidade}"
        })

        viewModel.cepResponseError.observe(this, Observer {
            Toast.makeText(this, "Message: ${it.errorCode}", Toast.LENGTH_LONG).show()
        })
    }
}
