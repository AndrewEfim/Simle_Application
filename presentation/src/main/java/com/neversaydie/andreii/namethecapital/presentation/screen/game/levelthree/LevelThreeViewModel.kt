package com.neversaydie.andreii.namethecapital.presentation.screen.game.levelthree

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

class LevelThreeViewModel : BaseViewModel<GameRouter>() {

    private val HELP_COUNT: Int = 4
    private val TIPS: Int = 3
    private val ATTEMPS: Int = 4
    private val WRONG_ANSWER_COUNT: Int = 4
    private val END_GAME: String = "Вы проиграли !"
    private val WRONG_ANSWER: String = "Ответ не верный"
    private val WIN_LEVEL_THREE: String = "Вы выиграли уровень №3"
    private val SHARED_COUNTER_RESULT_LVL_THREE = "GAME_RESULT_LVL_THREE"




    var sharedPref: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(App.instance)


    var countryName_level_three = ObservableField<String>("")
    var countryNameV_II_level_three = ObservableField<String>("")
    var capitalName_level_three = ObservableField<String>("")
    var capitalHelp_level_three = ObservableField<String>("4")
    var tips_level_three = ObservableField<String>("3")
    var attemps_level_three = ObservableField<String>("4")

    var answer_one_level_three = ObservableField<String>("")
    var answer_two_level_three = ObservableField<String>("")
    var answer_three_level_three = ObservableField<String>("")
    var answer_four_level_three = ObservableField<String>("")
    var score_level_three = ObservableField<String>("0")
    var counter_level_three = 0
    var helpCounter_level_three = 0
    var wrongAnswerCounter_level_three = 0

    private var randomOne: Int = 0
    private var randomTwo: Int = 0
    private var randomThree: Int = 0
    private var randomFour: Int = 0

    private val generated = HashSet<Int>()
    private val r = Random()
    private val generatedList = ArrayList<Int>()

    private val getCountryByContinentUseCase = UseCaseProvider.provideCountryByContinentListUseCase()

    private val getCountryUseCase = UseCaseProvider.provideCountryListUseCase()
    private var mAmericaCountry: ArrayList<Country> = ArrayList()
    private var mAllCountry: ArrayList<Country> = ArrayList()
    private var mOtherAmericaCity: ArrayList<String> = ArrayList()
    private val mCounter_level_three: MutableList<Int> = ArrayList()

    val continent = CountryByContinent("Америка")

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
                    mAmericaCountry.addAll(it)

                    mOtherAmericaCity.add(mAmericaCountry.get(counter_level_three).capital)
                    mOtherAmericaCity.add(mAmericaCountry.get(counter_level_three).otherCityOne)
                    mOtherAmericaCity.add(mAmericaCountry.get(counter_level_three).otherCityTwo)
                    mOtherAmericaCity.add(mAmericaCountry.get(counter_level_three).otherCityThree)
                    Log.d("myTag", "mCountry" + mAllCountry.size)
                    answer_one_level_three.set(mOtherAmericaCity.get(randomOne))
                    answer_two_level_three.set(mOtherAmericaCity.get(randomTwo))
                    answer_three_level_three.set(mOtherAmericaCity.get(randomThree))
                    answer_four_level_three.set(mOtherAmericaCity.get(randomFour))
                    countryName_level_three.set(mAllCountry.get(counter_level_three).country)

                    capitalHelp_level_three.set(mAllCountry.get(counter_level_three).capital)

                    Log.d("myLog", "random One" + randomOne)
                    Log.d("myLog", "random Two" + randomTwo)
                    Log.d("myLog", "random Three" + randomThree)
                    Log.d("myLog", "random Four" + randomFour)

                    Log.d("myLog", "mCountry" + mAllCountry.size.toString())
                    Log.d("myLog", "counter" + counter_level_three.toString())
                }))
    }

    fun checkAnswer() {

        capitalHelp_level_three.set(mAmericaCountry.get(counter_level_three).capital)

        if ((mAmericaCountry.get(counter_level_three).capital).equals(capitalName_level_three.get(), ignoreCase = true)) {
            counter_level_three++
            Log.d("myLog", "mCountry.size " + mAmericaCountry.size + " " + "counter " + counter_level_three)
            Log.d("myLog", "mCountry.size " + (counter_level_three == (mAmericaCountry.size - 1)))
            mCounter_level_three.add(counter_level_three)
            score_level_three.set(counter_level_three.toString())
            countryNameV_II_level_three.set("")
            countryName_level_three.set(mAmericaCountry.get(counter_level_three).country)
            setMOtherCity()
            Collections.shuffle(generatedList)
            randomOne = generatedList.get(0)
            randomTwo = generatedList.get(1)
            randomThree = generatedList.get(2)
            randomFour = generatedList.get(3)
            setAnswerButton()

            capitalHelp_level_three.set(mAmericaCountry.get(counter_level_three).capital)

        } else {
            countryNameV_II_level_three.set(WRONG_ANSWER)
            wrongAnswerCounter_level_three++

            Log.d("myLog", "answer is not correct")
            Log.d("myLog", "checkAnswer" + countryName_level_three.get() + "..." + capitalName_level_three.get())
            Log.d("myLog", "checkAnswer")
            Log.d("myLog", "counter" + counter_level_three.toString() + "..." + mCounter_level_three.size)
        }
        if (wrongAnswerCounter_level_three == WRONG_ANSWER_COUNT) {
            countryName_level_three.set(END_GAME)
//            if (counter_level_three > getResult()) {
//                saveResult()
//            }
            saveResult()

            router?.goToLogo()
        }
        attemps_level_three.set((ATTEMPS - wrongAnswerCounter_level_three).toString())
        tips_level_three.set((TIPS - helpCounter_level_three).toString())

        if (counter_level_three == (mAmericaCountry.size - 1)) {
            countryName_level_three.set(WIN_LEVEL_THREE)

            answer_one_level_three.set("")
            answer_two_level_three.set("")
            answer_three_level_three.set("")
            answer_four_level_three.set("")

            Log.d("myLog", "mCountry.size " + mAmericaCountry.size + " " + "counter " + counter_level_three)
            Log.d("myLog", "Win Game")
            saveResult()

            Timer().schedule(object : TimerTask() {
                override fun run() {
                    router?.goToLevelFour()
                }
            }, 2000)
        }
    }


    fun setMOtherCity() {
        this.mOtherAmericaCity.clear()
        this.mOtherAmericaCity.add(mAmericaCountry.get(counter_level_three).capital)
        this.mOtherAmericaCity.add(mAmericaCountry.get(counter_level_three).otherCityOne)
        this.mOtherAmericaCity.add(mAmericaCountry.get(counter_level_three).otherCityTwo)
        this.mOtherAmericaCity.add(mAmericaCountry.get(counter_level_three).otherCityThree)
    }

    fun setAnswerButton() {
        answer_one_level_three.set(this.mOtherAmericaCity.get(randomOne))
        answer_two_level_three.set(this.mOtherAmericaCity.get(randomTwo))
        answer_three_level_three.set(this.mOtherAmericaCity.get(randomThree))
        answer_four_level_three.set(this.mOtherAmericaCity.get(randomFour))
    }

    fun saveResult() {
        sharedPref?.edit()?.putInt(SHARED_COUNTER_RESULT_LVL_THREE, counter_level_three)?.apply()

    }

    fun getResult(): Int {
        val getResult: Int = sharedPref!!.getInt(SHARED_COUNTER_RESULT_LVL_THREE, 0)
        return getResult
    }

    fun asckHelp() {
        helpCounter_level_three++
        tips_level_three.set((TIPS - helpCounter_level_three).toString())
        if (helpCounter_level_three == HELP_COUNT) {
            capitalHelp_level_three.set("Подсказок больше нет")
        }
    }

    fun setAnswerOne() {
        capitalName_level_three.set(answer_one_level_three.get())
        Log.d("myLog", "setAnswer 1()" + capitalName_level_three.get())
    }

    fun setAnswerTwo() {
        capitalName_level_three.set(answer_two_level_three.get())
        Log.d("myLog", "setAnswer 2()" + capitalName_level_three.get())
    }

    fun setAnswerThree() {
        capitalName_level_three.set(answer_three_level_three.get())
        Log.d("myLog", "setAnswer 3()" + capitalName_level_three.get())
    }

    fun setAnswerFour() {
        capitalName_level_three.set(answer_four_level_three.get())
        Log.d("myLog", "setAnswer 4()" + capitalName_level_three.get())
    }


}