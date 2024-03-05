package com.example.calcjuros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import com.example.juroscalculator.R

import kotlin.math.pow


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun btnSimples(view: View) {
        val aa = findViewById<RadioButton>(R.id.radioAa)
        val editfinanciamento = findViewById<EditText>(R.id.editFinaciamento)
        val edittaxa = findViewById<EditText>(R.id.editTaxa)
        val edittempo = findViewById<EditText>(R.id.editTempo)
        val financiamento = editfinanciamento?.text.toString().toDouble()
        val taxa = edittaxa?.text.toString().toDouble()
        val tempo = edittempo?.text.toString().toDouble()
        var calcmont = 0.0
        var calcvalor = 0.0
        var calcparcela = 0.0
        if (aa.isSelected){
            val taxaAA = taxa / 12.0
            calcmont = financiamento + (financiamento * taxaAA * tempo)
            calcvalor = calcmont - financiamento
            calcparcela = calcmont / tempo
        }
        else {
            calcmont = financiamento + (financiamento * taxa * tempo)
            calcvalor = calcmont - financiamento
            calcparcela = calcmont / tempo
        }

        val intent = Intent(this, ResultadoActivity::class.java).apply {
            putExtra("calcmont", calcmont)
            putExtra("calcvalor", calcvalor)
            putExtra("calcparcela", calcparcela)
        }
        startActivity(intent)
    }
    fun btnComposto(view: View) {
        val aa = findViewById<RadioButton>(R.id.radioAa)
        val editfinanciamento = findViewById<EditText>(R.id.editFinaciamento)
        val edittaxa = findViewById<EditText>(R.id.editTaxa)
        val edittempo = findViewById<EditText>(R.id.editTempo)
        val financiamento = editfinanciamento?.text.toString().toDouble()
        val taxa = edittaxa?.text.toString().toDouble()
        val tempo = edittempo?.text.toString().toDouble()
        var calcmont = 0.0
        var calcvalor = 0.0
        var calcparcela = 0.0
        if (aa.isSelected){
            val taxaAA = Math.pow(1+taxa,(1 / 12.0))-1
            calcmont = financiamento * (1.0+taxaAA).pow(tempo)
            calcvalor = calcmont - financiamento
            calcparcela = calcmont / tempo
        }
        else {
            calcmont = financiamento * (1.0+taxa).pow(tempo)
            calcvalor = calcmont - financiamento
            calcparcela = calcmont / tempo
        }
        val intent = Intent(this, ResultadoActivity::class.java).apply {
            putExtra("calcmont", calcmont)
            putExtra("calcvalor", calcvalor)
            putExtra("calcparcela", calcparcela)
        }
        startActivity(intent)
    }
}