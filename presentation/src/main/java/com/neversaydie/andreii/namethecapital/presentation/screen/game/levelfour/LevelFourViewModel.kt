package com.neversaydie.andreii.namethecapital.presentation.screen.game.levelfour

import android.content.SharedPreferences
import android.databinding.ObservableField
import android.preference.PreferenceManager
import android.util.Log
import com.neversaydie.andreii.domain.entity.Country
import com.neversaydie.andreii.domain.entity.CountryByContinent
import com.neversaydie.andreii.namethecapital.presentation.app.App
import com.neversaydie.andreii.namethecapital.presentation.base.BaseViewModel
import com.neversaydie.andreii.namethecapital.presentation.factories.UseCaseProvider
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import io.reactivex.rxkotlin.subscribeBy
import java.util.*
import kotlin.collections.ArrayList

class LevelFourViewModel : BaseViewModel<GameRouter>() {

    private val HELP_COUNT: Int = 4
    private val TIPS: Int = 3
    private val ATTEMPS: Int = 4
    private val WRONG_ANSWER_COUNT: Int = 4
    private val END_GAME: String = "Вы проиграли !"
    private val WRONG_ANSWER: String = "Ответ не верный"
    private val WIN_LEVEL_FOUR: String = "Вы прошли Уровень №4"

    private val SHARED_COUNTER_RESULT_LVL_FOUR = "GAME_RESULT_LVL_FOUR"


    var sharedPref: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(App.instance)


    var countryName_level_four = ObservableField<String>("")
    var countryNameV_II_level_four = ObservableField<String>("")
    var capitalName_level_four = ObservableField<String>("")
    var capitalHelp_level_four = ObservableField<String>("4")
    var tips_level_four = ObservableField<String>("3")
    var attemps_level_four = ObservableField<String>("4")

    var answer_one_level_four = ObservableField<String>("")
    var answer_two_level_four = ObservableField<String>("")
    var answer_three_level_four = ObservableField<String>("")
    var answer_four_level_four = ObservableField<String>("")
    var score_level_four = ObservableField<String>("0")
    var counter_level_four = 0
    var helpCounter_level_four = 0
    var wrongAnswerCounter_level_four = 0

    private var randomOne: Int = 0
    private var randomTwo: Int = 0
    private var randomThree: Int = 0
    private var randomFour: Int = 0

    private val generated = HashSet<Int>()
    private val r = Random()
    private val generatedList = ArrayList<Int>()

    private val getCountryByContinentUseCase = UseCaseProvider.provideCountryByContinentListUseCase()

    private val getCountryUseCase = UseCaseProvider.provideCountryListUseCase()
    private var mAfricaCountry: ArrayList<Country> = ArrayList()
    private var mAllCountry: ArrayList<Country> = ArrayList()
    private var mOtherAfricaCity: ArrayList<String> = ArrayList()
    private val mCounter_level_four: MutableList<Int> = ArrayList()

    val continent = CountryByContinent("Африка")

    init {
        while (generated.size < 4) {
            generated.add(r.nextInt(4))
        }

        generatedList.addAll(generated)

        randomOne = generatedList.get(0)
        randomTwo = generatedList.get(1)
        randomThree = generatedList.get(2)
        randomFour = generatedList.get(3)




        addToDisposable(getCountryByContinentUseCase.getByContinent(continent)
                .subscribeBy(onNext = {
                    mAllCountry.addAll(it)
                    mAfricaCountry.addAll(it)

                    mOtherAfricaCity.add(mAfricaCountry.get(counter_level_four).capital)
                    mOtherAfricaCity.add(mAfricaCountry.get(counter_level_four).otherCityOne)
                    mOtherAfricaCity.add(mAfricaCountry.get(counter_level_four).otherCityTwo)
                    mOtherAfricaCity.add(mAfricaCountry.get(counter_level_four).otherCityThree)

                    answer_one_level_four.set(mOtherAfricaCity.get(randomOne))
                    answer_two_level_four.set(mOtherAfricaCity.get(randomTwo))
                    answer_three_level_four.set(mOtherAfricaCity.get(randomThree))
                    answer_four_level_four.set(mOtherAfricaCity.get(randomFour))
                    countryName_level_four.set(mAllCountry.get(counter_level_four).country)
                    capitalHelp_level_four.set(mAllCountry.get(counter_level_four).capital)

                }))
    }

    fun checkAnswer() {

        capitalHelp_level_four.set(mAfricaCountry.get(counter_level_four).capital)

        if ((mAfricaCountry.get(counter_level_four).capital).equals(capitalName_level_four.get(), ignoreCase = true)) {
            counter_level_four++
            mCounter_level_four.add(counter_level_four)
            score_level_four.set(counter_level_four.toString())
            countryNameV_II_level_four.set("")
            countryName_level_four.set(mAfricaCountry.get(counter_level_four).country)
            setMOtherCity()
            Collections.shuffle(generatedList)
            randomOne = generatedList.get(0)
            randomTwo = generatedList.get(1)
            randomThree = generatedList.get(2)
            randomFour = generatedList.get(3)
            setAnswerButton()

            capitalHelp_level_four.set(mAfricaCountry.get(counter_level_four).capital)

        } else {
            countryNameV_II_level_four.set(WRONG_ANSWER)
            wrongAnswerCounter_level_four++

        }
        if (wrongAnswerCounter_level_four == WRONG_ANSWER_COUNT) {
            countryName_level_four.set(END_GAME)
//            if (counter_level_four > getResult()) {
//                saveResult()
//            }
            saveResult()

            router?.goToLogo()
        }
        attemps_level_four.set((ATTEMPS - wrongAnswerCounter_level_four).toString())
        tips_level_four.set((TIPS - helpCounter_level_four).toString())

        if (counter_level_four == (mAfricaCountry.size - 1)) {
            countryName_level_four.set(WIN_LEVEL_FOUR)

            answer_one_level_four.set("")
            answer_two_level_four.set("")
            answer_three_level_four.set("")
            answer_four_level_four.set("")

            saveResult()

            Timer().schedule(object : TimerTask() {
                override fun run() {
                    router?.goToLevelFive()
                }
            }, 2000)
        }
    }


    fun setMOtherCity() {
        this.mOtherAfricaCity.clear()
        this.mOtherAfricaCity.add(mAfricaCountry.get(counter_level_four).capital)
        this.mOtherAfricaCity.add(mAfricaCountry.get(counter_level_four).otherCityOne)
        this.mOtherAfricaCity.add(mAfricaCountry.get(counter_level_four).otherCityTwo)
        this.mOtherAfricaCity.add(mAfricaCountry.get(counter_level_four).otherCityThree)
    }

    fun setAnswerButton() {
        answer_one_level_four.set(this.mOtherAfricaCity.get(randomOne))
        answer_two_level_four.set(this.mOtherAfricaCity.get(randomTwo))
        answer_three_level_four.set(this.mOtherAfricaCity.get(randomThree))
        answer_four_level_four.set(this.mOtherAfricaCity.get(randomFour))
    }

    fun saveResult() {
        sharedPref?.edit()?.putInt(SHARED_COUNTER_RESULT_LVL_FOUR, counter_level_four)?.apply()
    }

    fun getResult(): Int {
        val getResult: Int = sharedPref!!.getInt(SHARED_COUNTER_RESULT_LVL_FOUR, 0)
        return getResult
    }

    fun asckHelp() {
        helpCounter_level_four++
        tips_level_four.set((TIPS - helpCounter_level_four).toString())
        if (helpCounter_level_four == HELP_COUNT) {
            capitalHelp_level_four.set("Подсказок больше нет")
        }
    }

    fun setAnswerOne() {
        capitalName_level_four.set(answer_one_level_four.get())
    }

    fun setAnswerTwo() {
        capitalName_level_four.set(answer_two_level_four.get())
    }

    fun setAnswerThree() {
        capitalName_level_four.set(answer_three_level_four.get())
    }

    fun setAnswerFour() {
        capitalName_level_four.set(answer_four_level_four.get())
    }
}