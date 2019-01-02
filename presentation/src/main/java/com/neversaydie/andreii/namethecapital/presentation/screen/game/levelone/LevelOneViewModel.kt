package com.neversaydie.andreii.namethecapital.presentation.screen.game.levelone

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

class LevelOneViewModel : BaseViewModel<GameRouter>() {

    private val HELP_COUNT: Int = 4
    private val TIPS: Int = 3
    private val ATTEMPS: Int = 4
    private val WRONG_ANSWER_COUNT: Int = 4
    private val END_GAME: String = "Вы проиграли !"
    val WRONG_ANSWER: String = "Ответ не верный"
    private val WIN_LEVEL_ONE: String = "Вы выиграли Уровень №1"
    private val SHARED_COUNTER_RESULT = "GAME_RESULT_LVL_ONE"
    private val SHARED_COUNTER_RESULT_LVL_ONE = "GAME_RESULT_ONE"


    var sharedPref: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(App.instance)


    var countryName_level_one = ObservableField<String>("")
    var countryNameV_II_level_one = ObservableField<String>("")
    var capitalName_level_one = ObservableField<String>("")
    var capitalHelp_level_one = ObservableField<String>("4")
    var tips_level_one = ObservableField<String>("3")
    var attemps_level_one = ObservableField<String>("4")

    var answer_one_level_one = ObservableField<String>("")
    var answer_two_level_one = ObservableField<String>("")
    var answer_three_level_one = ObservableField<String>("")
    var answer_four_level_one = ObservableField<String>("")
    var score_level_one = ObservableField<String>("0")
    var counter_level_one = 0
    var helpCounter_level_one = 0
    var wrongAnswerCounter_level_one = 0

    private var randomOne: Int = 0
    private var randomTwo: Int = 0
    private var randomThree: Int = 0
    private var randomFour: Int = 0

    private val generated = HashSet<Int>()
    private val r = Random()
    private val generatedList = ArrayList<Int>()

    private val getCountryByContinentUseCase = UseCaseProvider.provideCountryByContinentListUseCase()


    private var mEuropeCountry: ArrayList<Country> = ArrayList()
    private var mAllCountry: ArrayList<Country> = ArrayList()
    private var mOtherEuropeCity: ArrayList<String> = ArrayList()
    private val mCounter_level_one: MutableList<Int> = ArrayList()

    val continent = CountryByContinent("Европа")

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
                    mEuropeCountry.addAll(it)

                    mOtherEuropeCity.add(mAllCountry.get(counter_level_one).capital)
                    mOtherEuropeCity.add(mAllCountry.get(counter_level_one).otherCityOne)
                    mOtherEuropeCity.add(mAllCountry.get(counter_level_one).otherCityTwo)
                    mOtherEuropeCity.add(mAllCountry.get(counter_level_one).otherCityThree)
                    Log.d("myTag", "mCountry" + mAllCountry.size)
                    answer_one_level_one.set(mOtherEuropeCity.get(randomOne))
                    answer_two_level_one.set(mOtherEuropeCity.get(randomTwo))
                    answer_three_level_one.set(mOtherEuropeCity.get(randomThree))
                    answer_four_level_one.set(mOtherEuropeCity.get(randomFour))
                    countryName_level_one.set(mAllCountry.get(counter_level_one).country)

                    capitalHelp_level_one.set(mAllCountry.get(counter_level_one).capital)

                    Log.d("myLog", "random One" + randomOne)
                    Log.d("myLog", "random Two" + randomTwo)
                    Log.d("myLog", "random Three" + randomThree)
                    Log.d("myLog", "random Four" + randomFour)

                    Log.d("myLog", "mCountry" + mAllCountry.size.toString())
                    Log.d("myLog", "counter" + counter_level_one.toString())
                }))
    }

    fun checkAnswer() {

        capitalHelp_level_one.set(mEuropeCountry.get(counter_level_one).capital)

        if ((mEuropeCountry.get(counter_level_one).capital).equals(capitalName_level_one.get(), ignoreCase = true)) {
            counter_level_one++
            Log.d("myLog", "mCountry.size " + mEuropeCountry.size + " " + "counter " + counter_level_one)
            Log.d("myLog", "mCountry.size " + (counter_level_one == (mEuropeCountry.size - 1)))
            mCounter_level_one.add(counter_level_one)
            score_level_one.set(counter_level_one.toString())
            countryNameV_II_level_one.set("")
            countryName_level_one.set(mEuropeCountry.get(counter_level_one).country)
            setMOtherCity()
            Collections.shuffle(generatedList)
            randomOne = generatedList.get(0)
            randomTwo = generatedList.get(1)
            randomThree = generatedList.get(2)
            randomFour = generatedList.get(3)
            setAnswerButton()

            capitalHelp_level_one.set(mEuropeCountry.get(counter_level_one).capital)

        } else {
            countryNameV_II_level_one.set(WRONG_ANSWER)
            wrongAnswerCounter_level_one++

            Log.d("myLog", "answer is not correct")
            Log.d("myLog", "checkAnswer" + countryName_level_one.get() + "..." + capitalName_level_one.get())
            Log.d("myLog", "checkAnswer")
            Log.d("myLog", "counter" + counter_level_one.toString() + "..." + mCounter_level_one.size)
        }
        if (wrongAnswerCounter_level_one == WRONG_ANSWER_COUNT) {
            countryName_level_one.set(END_GAME)
//            if (counter_level_one > getResult()) {
//                saveResult()
//            }
            saveResult()
            router?.goToLogo()
        }
        attemps_level_one.set((ATTEMPS - wrongAnswerCounter_level_one).toString())
        tips_level_one.set((TIPS - helpCounter_level_one).toString())

        if (counter_level_one == (mEuropeCountry.size - 1)) {
            countryName_level_one.set(WIN_LEVEL_ONE)

            answer_one_level_one.set("")
            answer_two_level_one.set("")
            answer_three_level_one.set("")
            answer_four_level_one.set("")

            Log.d("myLog", "mCountry.size " + mEuropeCountry.size + " " + "counter " + counter_level_one)
            Log.d("myLog", "Win Game")
            saveResult()

            Timer().schedule(object : TimerTask() {
                override fun run() {
                    //router?.goToLogo()
                    router?.goToLevelTwo()
                }
            }, 2000)
        }
    }


    fun setMOtherCity() {
        this.mOtherEuropeCity.clear()
        this.mOtherEuropeCity.add(mEuropeCountry.get(counter_level_one).capital)
        this.mOtherEuropeCity.add(mEuropeCountry.get(counter_level_one).otherCityOne)
        this.mOtherEuropeCity.add(mEuropeCountry.get(counter_level_one).otherCityTwo)
        this.mOtherEuropeCity.add(mEuropeCountry.get(counter_level_one).otherCityThree)
    }

    fun setAnswerButton() {
        answer_one_level_one.set(this.mOtherEuropeCity.get(randomOne))
        answer_two_level_one.set(this.mOtherEuropeCity.get(randomTwo))
        answer_three_level_one.set(this.mOtherEuropeCity.get(randomThree))
        answer_four_level_one.set(this.mOtherEuropeCity.get(randomFour))
    }

    fun saveResult() {
        sharedPref?.edit()?.putInt(SHARED_COUNTER_RESULT_LVL_ONE, counter_level_one)?.apply()

    }

    fun getResult(): Int {
        val getResult: Int = sharedPref!!.getInt(SHARED_COUNTER_RESULT, 0)
        return getResult
    }

    fun asckHelp() {
        helpCounter_level_one++
        tips_level_one.set((TIPS - helpCounter_level_one).toString())
        if (helpCounter_level_one == HELP_COUNT) {
            capitalHelp_level_one.set("Подсказок больше нет")
        }
    }

    fun setAnswerOne() {
        capitalName_level_one.set(answer_one_level_one.get())
        Log.d("myLog", "setAnswer 1()" + capitalName_level_one.get())
    }

    fun setAnswerTwo() {
        capitalName_level_one.set(answer_two_level_one.get())
        Log.d("myLog", "setAnswer 2()" + capitalName_level_one.get())
    }

    fun setAnswerThree() {
        capitalName_level_one.set(answer_three_level_one.get())
        Log.d("myLog", "setAnswer 3()" + capitalName_level_one.get())
    }

    fun setAnswerFour() {
        capitalName_level_one.set(answer_four_level_one.get())
        Log.d("myLog", "setAnswer 4()" + capitalName_level_one.get())
    }


}