package com.neversaydie.andreii.namethecapital.presentation.screen.game

import android.content.SharedPreferences
import android.databinding.ObservableField
import android.graphics.Color
import android.graphics.Color.GREEN
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import com.neversaydie.andreii.domain.entity.Country
import com.neversaydie.andreii.namethecapital.presentation.app.App
import com.neversaydie.andreii.namethecapital.presentation.base.BaseViewModel
import com.neversaydie.andreii.namethecapital.presentation.factories.UseCaseProvider
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import io.reactivex.rxkotlin.subscribeBy
import java.util.*
import kotlin.collections.ArrayList


class GameViewModel : BaseViewModel<GameRouter>() {

    private val HELP_COUNT: Int = 4
    private val TIPS: Int = 3
    private val ATTEMPS: Int = 4
    private val WRONG_ANSWER_COUNT: Int = 4
    private val END_GAME: String = "Вы проиграли !"
    private val WRONG_ANSWER: String = "Ответ не верный"
    private val WIN_GAME: String = "Вы выиграли"
    private val SHARED_COUNTER_RESULT = "GAME_RESULT"


    var sharedPref: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(App.instance)

    private val mCountry: MutableList<Country> = mutableListOf()
    private val mCounter: MutableList<Int> = ArrayList()
    private val mOtherCity: MutableList<String> = ArrayList()
    private val getCountryUseCase = UseCaseProvider.provideCountryListUseCase()
    var score = ObservableField<String>("0")
    var counter = 0
    var helpCounter = 0
    var wrongAnswerCounter = 0
    var countryName = ObservableField<String>("")
    var countryNameV_II = ObservableField<String>("")
    var capitalName = ObservableField<String>("")
    var capitalHelp = ObservableField<String>("4")
    var tips = ObservableField<String>("3")
    var attemps = ObservableField<String>("4")

    var answer_one = ObservableField<String>("")
    var answer_two = ObservableField<String>("")
    var answer_three = ObservableField<String>("")
    var answer_four = ObservableField<String>("")

    var randomOne: Int
    var randomTwo: Int
    var randomThree: Int
    var randomFour: Int

    val generated = HashSet<Int>()
    val r = Random()
    val generatedList = ArrayList<Int>()

    init {
        while (generated.size < 4) {
            generated.add(r.nextInt(4))
        }
        generatedList.addAll(generated)

        randomOne = generatedList.get(0)
        randomTwo = generatedList.get(1)
        randomThree = generatedList.get(2)
        randomFour = generatedList.get(3)


        addToDisposable(getCountryUseCase.get()
                .subscribeBy(onNext = {
                    mCountry.addAll(it)

                    mOtherCity.add(mCountry.get(counter).capital)
                    mOtherCity.add(mCountry.get(counter).otherCityOne)
                    mOtherCity.add(mCountry.get(counter).otherCityTwo)
                    mOtherCity.add(mCountry.get(counter).otherCityThree)
                    answer_one.set(mOtherCity.get(randomOne))
                    answer_two.set(mOtherCity.get(randomTwo))
                    answer_three.set(mOtherCity.get(randomThree))
                    answer_four.set(mOtherCity.get(randomFour))
                    countryName.set(mCountry.get(counter).country)

                    capitalHelp.set(mCountry.get(counter).capital)

                }))
    }

    fun checkAnswer() {

        capitalHelp.set(mCountry.get(counter).capital)

        if ((mCountry.get(counter).capital).equals(capitalName.get(), ignoreCase = true)) {
            counter++
            mCounter.add(counter)
            score.set(counter.toString())
            countryNameV_II.set("")
            countryName.set(mCountry.get(counter).country)
            setMOtherCity()
            Collections.shuffle(generatedList)
            randomOne = generatedList.get(0)
            randomTwo = generatedList.get(1)
            randomThree = generatedList.get(2)
            randomFour = generatedList.get(3)
            setAnswerButton()

            capitalHelp.set(mCountry.get(counter).capital)

        } else {
            countryNameV_II.set(WRONG_ANSWER)
            wrongAnswerCounter++

        }
        if (wrongAnswerCounter == WRONG_ANSWER_COUNT) {
            countryName.set(END_GAME)
            if (counter > getResult()) {
                saveResult()
            }

            router?.goToLogo()
        }
        attemps.set((ATTEMPS - wrongAnswerCounter).toString())
        tips.set((TIPS - helpCounter).toString())

        if (counter == (mCountry.size - 1)) {
            countryName.set(WIN_GAME)

            answer_one.set("")
            answer_two.set("")
            answer_three.set("")
            answer_four.set("")

            saveResult()

            Timer().schedule(object : TimerTask() {
                override fun run() {
                    router?.goToLogo()
                }
            }, 2000)
        }
    }


    fun setMOtherCity() {
        mOtherCity.clear()
        mOtherCity.add(mCountry.get(counter).capital)
        mOtherCity.add(mCountry.get(counter).otherCityOne)
        mOtherCity.add(mCountry.get(counter).otherCityTwo)
        mOtherCity.add(mCountry.get(counter).otherCityThree)
    }

    fun setAnswerButton() {
        answer_one.set(mOtherCity.get(randomOne))
        answer_two.set(mOtherCity.get(randomTwo))
        answer_three.set(mOtherCity.get(randomThree))
        answer_four.set(mOtherCity.get(randomFour))
    }

    fun saveResult() {
        sharedPref?.edit()?.putInt(SHARED_COUNTER_RESULT, counter)?.apply()
    }

    fun getResult(): Int {
        val getResult: Int = sharedPref!!.getInt(SHARED_COUNTER_RESULT, 0)
        return getResult
    }

    fun asckHelp() {
        helpCounter++
        tips.set((TIPS - helpCounter).toString())
        if (helpCounter == HELP_COUNT) {
            capitalHelp.set("Подсказок больше нет")
        }
    }

    fun setAnswerOne() {
        capitalName.set(answer_one.get())
    }

    fun setAnswerTwo() {
        capitalName.set(answer_two.get())
    }

    fun setAnswerThree() {
        capitalName.set(answer_three.get())
    }

    fun setAnswerFour() {
        capitalName.set(answer_four.get())
    }

}