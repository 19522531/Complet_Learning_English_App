package com.batdaulaptrinh.completlearningenglishapp.ui.game

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.service.media.MediaBrowserService
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
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
        var count = 1
        var correct = 0
        var incorrect = 0
        var check = true
        var randomValues: List<Int> = List(4) { Random.nextInt(0, 3) }
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



        if (count == 1)
        {
            listword = listword2.asSequence().shuffled().take(20).toList()
            var decodedImageA = decode( 0, listword)
            binding.answerAImg.setImageBitmap(decodedImageA)
            var decodedImageB = decode(1, listword)
            binding.answerBImg.setImageBitmap(decodedImageB)
            var decodedImageC = decode(2, listword)
            binding.answerCImg.setImageBitmap(decodedImageC)
            var decodedImageD = decode(3, listword)
            binding.answerDImg.setImageBitmap(decodedImageD)

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
            listword = listword2.asSequence().shuffled().take(20).toList()
            val decodedImageA = decode( 0, listword)
            binding.answerAImg.setImageBitmap(decodedImageA)
            val decodedImageB = decode(1, listword)
            binding.answerBImg.setImageBitmap(decodedImageB)
            val decodedImageC = decode(2, listword)
            binding.answerCImg.setImageBitmap(decodedImageC)
            val decodedImageD = decode(3, listword)
            binding.answerDImg.setImageBitmap(decodedImageD)
            entext  = listword[Companion.randomValues.asSequence().shuffled().take(1).toList()[0]].en_word
            binding.enWordTxt.setText(entext)
            Log.e("ENTEXT", entext)
            if (correct + incorrect != count-1)
            {
                incorrect+=1
            }
            binding.numberCorrectAnswerTxt.setText("Correct: "+ correct.toString())
            binding.numberIncorrectAnswerTxt.setText("Incorrect: " + incorrect.toString())
            binding.progressTxt.setText(count.toString()+"/20")
            binding.ansTxt.setText("")
            check = true
            binding.answerATxt.setBackgroundResource(R.drawable.boder_image_none)
            binding.answerBTxt.setBackgroundResource(R.drawable.boder_image_none)
            binding.answerCTxt.setBackgroundResource(R.drawable.boder_image_none)
            binding.answerDTxt.setBackgroundResource(R.drawable.boder_image_none)
        }

        binding.answerATxt.setOnClickListener()
        {
            if (check)
                {   binding.checkBtn.visibility
                    if (listword[0].en_word == entext) {
                        correct += 1
                        binding.numberCorrectAnswerTxt.setText("Correct: " + correct.toString())
                        binding.answerATxt.setBackgroundResource(R.drawable.boder_image)
                        binding.ansTxt.setText("Yeeh correct")

                    } else {
                        incorrect += 1
                        binding.numberIncorrectAnswerTxt.setText("Incorrect: " + incorrect.toString())
                        if (listword[1].en_word==entext)
                        {
                            binding.ansTxt.setText("Oh no incorrect    answer is B")
                            binding.answerBTxt.setBackgroundResource(R.drawable.boder_image_red)
                        }
                        else if (listword[2].en_word==entext)
                        {
                            binding.ansTxt.setText("Oh no incorrect    answer is C")
                            binding.answerCTxt.setBackgroundResource(R.drawable.boder_image_red)
                        }
                        else if (listword[3].en_word==entext)
                        {
                            binding.ansTxt.setText("Oh no incorrect  answer is D")
                            binding.answerDTxt.setBackgroundResource(R.drawable.boder_image_red)
                        }

                    }
                    check = false
                }
        }
        binding.answerBTxt.setOnClickListener()
        {
            if (check) {
                if (listword[1].en_word == entext) {
                    correct += 1
                    binding.numberCorrectAnswerTxt.setText("Correct: " + correct.toString())
                    binding.ansTxt.setText("Yeeh correct")
                    binding.answerBTxt.setBackgroundResource(R.drawable.boder_image)
                } else {
                    incorrect += 1
                    binding.numberIncorrectAnswerTxt.setText("Incorrect: " + incorrect.toString())
                    if (listword[0].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is A")
                        binding.answerATxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[2].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is C")
                        binding.answerCTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[3].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect  answer is D")
                        binding.answerDTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                }
                check = false
            }

        }
        binding.answerCTxt.setOnClickListener()
        {
            if (check) {
                if (listword[2].en_word == entext) {
                    correct += 1
                    binding.numberCorrectAnswerTxt.setText("Correct: " + correct.toString())
                    binding.ansTxt.setText("Yeeh correct")
                    binding.answerCTxt.setBackgroundResource(R.drawable.boder_image)

                } else {
                    incorrect += 1
                    binding.numberIncorrectAnswerTxt.setText("Incorrect: " + incorrect.toString())
                    if (listword[0].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is A")
                        binding.answerATxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[1].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is B")
                        binding.answerBTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[3].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is D")
                        binding.answerDTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                }
                check = false
            }
        }
        binding.answerDTxt.setOnClickListener()
        {
            if (check) {
                if (listword[3].en_word == entext) {
                    correct += 1
                    binding.numberCorrectAnswerTxt.setText("Correct: " + correct.toString())
                    binding.ansTxt.setText("Yeeh correct")
                    binding.answerDTxt.setBackgroundResource(R.drawable.boder_image)
                } else {
                    incorrect += 1
                    binding.numberIncorrectAnswerTxt.setText("Incorrect: " + incorrect.toString())
                    if (listword[0].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is A")
                        binding.answerATxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[1].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is B")
                        binding.answerBTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[2].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is C")
                        binding.answerCTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                }
                check = false
            }
        }
        binding.answerAImg.setOnClickListener()
        {
            if (check)
            {   binding.checkBtn.visibility
                if (listword[0].en_word == entext) {
                    correct += 1
                    binding.numberCorrectAnswerTxt.setText("Correct: " + correct.toString())
                    binding.answerATxt.setBackgroundResource(R.drawable.boder_image)
                    binding.ansTxt.setText("Yeeh correct")

                } else {
                    incorrect += 1
                    binding.numberIncorrectAnswerTxt.setText("Incorrect: " + incorrect.toString())
                    if (listword[1].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect    answer is B")
                        binding.answerBTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[2].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect    answer is C")
                        binding.answerCTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[3].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect  answer is D")
                        binding.answerDTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }

                }
                check = false
            }
        }
        binding.answerBImg.setOnClickListener()
        {
            if (check) {
                if (listword[1].en_word == entext) {
                    correct += 1
                    binding.numberCorrectAnswerTxt.setText("Correct: " + correct.toString())
                    binding.ansTxt.setText("Yeeh correct")
                    binding.answerBTxt.setBackgroundResource(R.drawable.boder_image)
                } else {
                    incorrect += 1
                    binding.numberIncorrectAnswerTxt.setText("Incorrect: " + incorrect.toString())
                    if (listword[0].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is A")
                        binding.answerATxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[2].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is C")
                        binding.answerCTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[3].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect  answer is D")
                        binding.answerDTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                }
                check = false
            }

        }
        binding.answerCImg.setOnClickListener()
        {
            if (check) {
                if (listword[2].en_word == entext) {
                    correct += 1
                    binding.numberCorrectAnswerTxt.setText("Correct: " + correct.toString())
                    binding.ansTxt.setText("Yeeh correct")
                    binding.answerCTxt.setBackgroundResource(R.drawable.boder_image)

                } else {
                    incorrect += 1
                    binding.numberIncorrectAnswerTxt.setText("Incorrect: " + incorrect.toString())
                    if (listword[0].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is A")
                        binding.answerATxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[1].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is B")
                        binding.answerBTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[3].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is D")
                        binding.answerDTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                }
                check = false
            }
        }
        binding.answerDImg.setOnClickListener()
        {
            if (check) {
                if (listword[3].en_word == entext) {
                    correct += 1
                    binding.numberCorrectAnswerTxt.setText("Correct: " + correct.toString())
                    binding.ansTxt.setText("Yeeh correct")
                    binding.answerDTxt.setBackgroundResource(R.drawable.boder_image)
                } else {
                    incorrect += 1
                    binding.numberIncorrectAnswerTxt.setText("Incorrect: " + incorrect.toString())
                    if (listword[0].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is A")
                        binding.answerATxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[1].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is B")
                        binding.answerBTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
                    else if (listword[2].en_word==entext)
                    {
                        binding.ansTxt.setText("Oh no incorrect   answer is C")
                        binding.answerCTxt.setBackgroundResource(R.drawable.boder_image_red)
                    }
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