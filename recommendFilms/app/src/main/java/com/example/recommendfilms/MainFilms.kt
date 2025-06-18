package com.example.recommendfilms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.* 
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recommendfilms.ui.theme.RecommendFilmsTheme

class MainFilms : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecommendFilmsTheme {
                TelaRecomendacao()
            }
        }
    }

    private fun recomendarFilmes(idade: Int): String {
        return when (idade) {
            in 0..12 -> "Recomendamos filmes infantis: Moana, Toy Story, Frozen."
            in 13..17 -> "Recomendamos filmes para adolescentes: Harry Potter, Homem-Aranha, Jogos Vorazes."
            else -> "Recomendamos filmes para adultos: O Poderoso Chefão, Clube da Luta, Matrix."
        }
    }

    @Composable
    fun TelaRecomendacao() {
        var idadeTexto by remember { mutableStateOf("") }
        var recomendacao by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Informe sua idade:")
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = idadeTexto,
                onValueChange = { idadeTexto = it },
                label = { Text("Idade") }, // Rótulo dentro do campo
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val idade = idadeTexto.toIntOrNull()
                recomendacao = if (idade != null && idade >= 0) {
                    recomendarFilmes(idade)
                } else {
                    "Por favor, insira uma idade válida."
                }
            }) {
                Text("Obter Recomendação")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = recomendacao)
        }
    }

}
