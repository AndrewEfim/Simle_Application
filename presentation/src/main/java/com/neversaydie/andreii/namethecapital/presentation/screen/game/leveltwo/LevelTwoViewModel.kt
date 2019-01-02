package com.neversaydie.andreii.namethecapital.presentation.screen.game.leveltwo

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

class LevelTwoViewModel : BaseViewModel<GameRouter>() {

    private val HELP_COUNT: Int = 4
    private val TIPS: Int = 3
    private val ATTEMPS: Int = 4
    private val WRONG_ANSWER_COUNT: Int = 4
    private val END_GAME: String = "Вы проиграли !"
    private val WRONG_ANSWER: String = "Ответ не верный"
    private val WIN_LEVEL_TWO: String = "Вы выиграли уровень №2"
    private val SHARED_COUNTER_RESULT_LVL_TWO = "GAME_RESULT_LVL_TWO"



    var sharedPref: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(App.instance)


    var countryName_level_two = ObservableField<String>("")
    var countryNameV_II_level_two = ObservableField<String>("")
    var capitalName_level_two = ObservableField<String>("")
    var capitalHelp_level_two = ObservableField<String>("4")
    var tips_level_two = ObservableField<String>("3")
    var attemps_level_two = ObservableField<String>("4")

    var answer_one_level_two = ObservableField<String>("")
    var answer_two_level_two = ObservableField<String>("")
    var answer_three_level_two = ObservableField<String>("")
    var answer_four_level_two = ObservableField<String>("")
    var score_level_two = ObservableField<String>("0")
    var counter_level_two = 0
    var helpCounter_level_two = 0
    var wrongAnswerCounter_level_two = 0

    private var randomOne: Int = 0
    private var randomTwo: Int = 0
    private var randomThree: Int = 0
    private var randomFour: Int = 0

    private val generated = HashSet<Int>()
    private val r = Random()
    private val generatedList = ArrayList<Int>()

    private val getCountryByContinentUseCase = UseCaseProvider.provideCountryByContinentListUseCase()

    private val getCountryUseCase = UseCaseProvider.provideCountryListUseCase()
    private var mAsiaCountry: ArrayList<Country> = ArrayList()
    private var mAllCountry: ArrayList<Country> = ArrayList()
    private var mOtherAsiaCity: ArrayList<String> = ArrayList()
    private val mCounter_level_two: MutableList<Int> = ArrayList()

    val continent = CountryByContinent("Азия")

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
                    mAsiaCountry.addAll(it)

                    mOtherAsiaCity.add(mAsiaCountry.get(counter_level_two).capital)
                    mOtherAsiaCity.add(mAsiaCountry.get(counter_level_two).otherCityOne)
                    mOtherAsiaCity.add(mAsiaCountry.get(counter_level_two).otherCityTwo)
                    mOtherAsiaCity.add(mAsiaCountry.get(counter_level_two).otherCityThree)
                    Log.d("myTag", "mCountry" + mAllCountry.size)
                    answer_one_level_two.set(mOtherAsiaCity.get(randomOne))
                    answer_two_level_two.set(mOtherAsiaCity.get(randomTwo))
                    answer_three_level_two.set(mOtherAsiaCity.get(randomThree))
                    answer_four_level_two.set(mOtherAsiaCity.get(randomFour))
                    countryName_level_two.set(mAllCountry.get(counter_level_two).country)

                    capitalHelp_level_two.set(mAllCountry.get(counter_level_two).capital)

                    Log.d("myLog", "random One" + randomOne)
                    Log.d("myLog", "random Two" + randomTwo)
                    Log.d("myLog", "random Three" + randomThree)
                    Log.d("myLog", "random Four" + randomFour)

                    Log.d("myLog", "mCountry" + mAllCountry.size.toString())
                    Log.d("myLog", "counter" + counter_level_two.toString())
                }))
    }

    fun checkAnswer() {

        capitalHelp_level_two.set(mAsiaCountry.get(counter_level_two).capital)

        if ((mAsiaCountry.get(counter_level_two).capital).equals(capitalName_level_two.get(), ignoreCase = true)) {
            counter_level_two++
            Log.d("myLog", "mCountry.size " + mAsiaCountry.size + " " + "counter " + counter_level_two)
            Log.d("myLog", "mCountry.size " + (counter_level_two == (mAsiaCountry.size - 1)))
            mCounter_level_two.add(counter_level_two)
            score_level_two.set(counter_level_two.toString())
            countryNameV_II_level_two.set("")
            countryName_level_two.set(mAsiaCountry.get(counter_level_two).country)
            setMOtherCity()
            Collections.shuffle(generatedList)
            randomOne = generatedList.get(0)
            randomTwo = generatedList.get(1)
            randomThree = generatedList.get(2)
            randomFour = generatedList.get(3)
            setAnswerButton()

            capitalHelp_level_two.set(mAsiaCountry.get(counter_level_two).capital)

        } else {
            countryNameV_II_level_two.set(WRONG_ANSWER)
            wrongAnswerCounter_level_two++

            Log.d("myLog", "answer is not correct")
            Log.d("myLog", "checkAnswer" + countryName_level_two.get() + "..." + capitalName_level_two.get())
            Log.d("myLog", "checkAnswer")
            Log.d("myLog", "counter" + counter_level_two.toString() + "..." + mCounter_level_two.size)
        }

        if (counter_level_two > 10) {
            saveResult()
        }

        if (wrongAnswerCounter_level_two == WRONG_ANSWER_COUNT) {
            countryName_level_two.set(END_GAME)

//            if (counter_level_two > getResult()) {
//                saveResult()
//            }
            saveResult()
            router?.goToLogo()
        }
        attemps_level_two.set((ATTEMPS - wrongAnswerCounter_level_two).toString())
        tips_level_two.set((TIPS - helpCounter_level_two).toString())


        if (counter_level_two == (mAsiaCountry.size-1)) {
            countryName_level_two.set(WIN_LEVEL_TWO)

            answer_one_level_two.set("")
            answer_two_level_two.set("")
            answer_three_level_two.set("")
            answer_four_level_two.set("")

            Log.d("myLog", "mCountry.size " + mAsiaCountry.size + " " + "counter " + counter_level_two)
            Log.d("myLog", "Win Game")

            saveResult()

            Timer().schedule(object : TimerTask() {
                override fun run() {
                    router?.goToLevelThree()
                }
            }, 2000)
        }
    }


    fun setMOtherCity() {
        this.mOtherAsiaCity.clear()
        this.mOtherAsiaCity.add(mAsiaCountry.get(counter_level_two).capital)
        this.mOtherAsiaCity.add(mAsiaCountry.get(counter_level_two).otherCityOne)
        this.mOtherAsiaCity.add(mAsiaCountry.get(counter_level_two).otherCityTwo)
        this.mOtherAsiaCity.add(mAsiaCountry.get(counter_level_two).otherCityThree)
    }

    fun setAnswerButton() {
        answer_one_level_two.set(this.mOtherAsiaCity.get(randomOne))
        answer_two_level_two.set(this.mOtherAsiaCity.get(randomTwo))
        answer_three_level_two.set(this.mOtherAsiaCity.get(randomThree))
        answer_four_level_two.set(this.mOtherAsiaCity.get(randomFour))
    }

    fun saveResult() {
        sharedPref?.edit()?.putInt(SHARED_COUNTER_RESULT_LVL_TWO, counter_level_two)?.apply()

    }

    fun getResult(): Int {
        val getResult: Int = sharedPref!!.getInt(SHARED_COUNTER_RESULT_LVL_TWO, 0)
        return getResult
    }

    fun asckHelp() {
        helpCounter_level_two++
        tips_level_two.set((TIPS - helpCounter_level_two).toString())
        if (helpCounter_level_two == HELP_COUNT) {
            capitalHelp_level_two.set("Подсказок больше нет")
        }
    }

    fun setAnswerOne() {
        capitalName_level_two.set(answer_one_level_two.get())
        Log.d("myLog", "setAnswer 1()" + capitalName_level_two.get())
    }

    fun setAnswerTwo() {
        capitalName_level_two.set(answer_two_level_two.get())
        Log.d("myLog", "setAnswer 2()" + capitalName_level_two.get())
    }

    fun setAnswerThree() {
        capitalName_level_two.set(answer_three_level_two.get())
        Log.d("myLog", "setAnswer 3()" + capitalName_level_two.get())
    }

    fun setAnswerFour() {
        capitalName_level_two.set(answer_four_level_two.get())
        Log.d("myLog", "setAnswer 4()" + capitalName_level_two.get())
    }


}