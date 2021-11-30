package com.batdaulaptrinh.completlearningenglishapp.ui.game

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.batdaulaptrinh.completlearningenglishapp.R
import com.batdaulaptrinh.completlearningenglishapp.databinding.FragmentMultipleChoiceBinding
import com.batdaulaptrinh.completlearningenglishapp.model.Word
import com.batdaulaptrinh.completlearningenglishapp.model.WordSet
import com.batdaulaptrinh.completlearningenglishapp.utils.Utils
import java.util.*
import kotlin.random.Random


class MultipleChoiceFragment : Fragment() {
    companion object {
        val KEY_AGRS_SET = "WORD_SET"
        var count = 0
        var correct = 0
        var incorrect = 0
        var check = true
        var randomValues: List<Int> = List(4) { Random.nextInt(0, 20) }
        var entext = ""
        lateinit var listword: List<Word>

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentMultipleChoiceBinding>(layoutInflater,
            R.layout.fragment_multiple_choice,
            container,
            false)

        arguments?.let {
            val setWord = it.get(KEY_AGRS_SET)
            if (setWord is WordSet) {
                binding.titleToolBar.text = setWord.setNth.toString()
            }
        }

        var listword2 = Utils.getWordList();



        if (count == 0)
        {
            listword = listword2.asSequence().shuffled().take(20).toList()
            randomValues = List(4) { Random.nextInt(0, 20) }
            var decodedImageA = decode( randomValues[0], listword)
            binding.answerATxt.setImageBitmap(decodedImageA)
            var decodedImageB = decode(randomValues[1], listword)
            binding.answerBTxt.setImageBitmap(decodedImageB)
            var decodedImageC = decode(randomValues[2], listword)
            binding.answerCTxt.setImageBitmap(decodedImageC)
            var decodedImageD = decode(randomValues[3], listword)
            binding.answerDTxt.setImageBitmap(decodedImageD)

            entext  = listword[randomValues.asSequence().shuffled().take(1).toList()[0]].en_word
            binding.enWordTxt.setText(entext)
            Log.e("ENTEXT", entext)
            binding.numberCorrectAnswerTxt.setText("Correct: "+ correct.toString())
            binding.numberIncorrectAnswerTxt.setText("Incorrect: " + incorrect.toString())
            binding.progressTxt.setText(count.toString()+"/20")
        }
        binding.checkBtn.setOnClickListener()
        {
            count +=1
            randomValues = List(4) { Random.nextInt(0, 20) }
            listword = listword2.asSequence().shuffled().take(20).toList()
            val decodedImageA = decode( randomValues[0], listword)
            binding.answerATxt.setImageBitmap(decodedImageA)
            val decodedImageB = decode(randomValues[1], listword)
            binding.answerBTxt.setImageBitmap(decodedImageB)
            val decodedImageC = decode(randomValues[2], listword)
            binding.answerCTxt.setImageBitmap(decodedImageC)
            val decodedImageD = decode(randomValues[3], listword)
            binding.answerDTxt.setImageBitmap(decodedImageD)
            entext  = listword[Companion.randomValues.asSequence().shuffled().take(1).toList()[0]].en_word
            binding.enWordTxt.setText(entext)
            Log.e("ENTEXT", entext)
            binding.numberCorrectAnswerTxt.setText("Correct: "+ correct.toString())
            binding.numberIncorrectAnswerTxt.setText("Incorrect: " + incorrect.toString())
            binding.progressTxt.setText(count.toString()+"/20")
            check = true
        }
        if (check)
        {
            binding.answerATxt.setOnClickListener()
            {
                if (listword[randomValues[0]].en_word == entext) {
                    correct +=1
                    binding.numberCorrectAnswerTxt.setText("Correct: " + correct.toString())

                }
                check = false
            }
        }
        binding.backwardImg.setOnClickListener{
            findNavController().popBackStack()
        }

        return binding.root
    }
    fun decode(i: Int, listword: List<Word>) :Bitmap
    {
        Log.e("ANS", listword[i].en_word)
        val imageBytes = Base64.decode(listword[i].thumbnail, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0,imageBytes.size)
        return decodedImage

    }


}