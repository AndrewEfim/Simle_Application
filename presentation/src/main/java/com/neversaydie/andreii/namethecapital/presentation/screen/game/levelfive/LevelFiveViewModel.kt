package com.neversaydie.andreii.namethecapital.presentation.screen.game.levelfive

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

class LevelFiveViewModel : BaseViewModel<GameRouter>() {

    private val HELP_COUNT: Int = 4
    private val TIPS: Int = 3
    private val ATTEMPS: Int = 4
    private val WRONG_ANSWER_COUNT: Int = 4
    private val END_GAME: String = "Вы проиграли !"
    private val WRONG_ANSWER: String = "Ответ не верный"
    private val WIN_LEVEL_FIVE: String = "Вы прошли Уровень №5"
    private val WIN_GAME: String = "Вы выиграли!"
    private val SHARED_COUNTER_RESULT_LVL_FIVE = "GAME_RESULT_LVL_FIVE"


    var sharedPref: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(App.instance)

    var flagUrl_level_five = ObservableField<String>("http://www.world-globe.ru/files/flags/australia_l.png")

    var countryName_level_five = ObservableField<String>("")
    var countryNameV_II_level_five = ObservableField<String>("")
    var capitalName_level_five = ObservableField<String>("")
    var capitalHelp_level_five = ObservableField<String>("4")
    var tips_level_five = ObservableField<String>("3")
    var attemps_level_five = ObservableField<String>("4")

    var answer_one_level_five = ObservableField<String>("")
    var answer_two_level_five = ObservableField<String>("")
    var answer_three_level_five = ObservableField<String>("")
    var answer_four_level_five = ObservableField<String>("")
    var score_level_five = ObservableField<String>("0")
    var counter_level_five = 0
    var helpCounter_level_five = 0
    var wrongAnswerCounter_level_five = 0

    private var randomOne: Int = 0
    private var randomTwo: Int = 0
    private var randomThree: Int = 0
    private var randomFour: Int = 0

    private var randomCounterOne: Int
    private var randomCounterTwo: Int
    private var randomCounterThree: Int

    private val generated = HashSet<Int>()
    private val generatedCountry = HashSet<Int>()

    private val r = Random()
    private val generatedList = ArrayList<Int>()
    private val generatedListCountry = ArrayList<Int>()

    private val getCountryByContinentUseCase = UseCaseProvider.provideCountryByContinentListUseCase()

    private val getCountryUseCase = UseCaseProvider.provideCountryListUseCase()

    private var mAllCountry: ArrayList<Country> = ArrayList()
    private var mOtherCountry: ArrayList<String> = ArrayList()
    private val mCounter_level_five: MutableList<Int> = ArrayList()


    init {
        while (generated.size < 4) {
            generated.add(r.nextInt(4))
        }
//FIXME
        while (generatedCountry.size < 99) {
            generatedCountry.add(r.nextInt(99))
        }

        generatedList.addAll(generated)
        generatedListCountry.addAll(generatedCountry)
        randomOne = generatedList.get(0)
        randomTwo = generatedList.get(1)
        randomThree = generatedList.get(2)
        randomFour = generatedList.get(3)

        randomCounterOne = generatedListCountry.get(r.nextInt(99))
        randomCounterTwo = generatedListCountry.get(r.nextInt(99))
        randomCounterThree = generatedListCountry.get(r.nextInt(99))

        addToDisposable(getCountryUseCase.get()
                .subscribeBy(onNext = {
                    mAllCountry.addAll(it)

                    mOtherCountry.add(mAllCountry.get(counter_level_five).country)
                    mOtherCountry.add(mAllCountry.get(randomCounterOne).country)
                    mOtherCountry.add(mAllCountry.get(randomCounterTwo).country)
                    mOtherCountry.add(mAllCountry.get(randomCounterThree).country)

                    answer_one_level_five.set(mOtherCountry.get(randomOne))
                    answer_two_level_five.set(mOtherCountry.get(randomTwo))
                    answer_three_level_five.set(mOtherCountry.get(randomThree))
                    answer_four_level_five.set(mOtherCountry.get(randomFour))

                    flagUrl_level_five.set(mAllCountry.get(counter_level_five).flag)
                    countryName_level_five.set(mAllCountry.get(counter_level_five).country)

                    capitalHelp_level_five.set(mAllCountry.get(counter_level_five).country)
                }))
    }

    fun checkAnswer() {

        capitalHelp_level_five.set(mAllCountry.get(counter_level_five).country)

        if ((mAllCountry.get(counter_level_five).country).equals(capitalName_level_five.get(), ignoreCase = true)) {
            counter_level_five++
            mCounter_level_five.add(counter_level_five)
            score_level_five.set(counter_level_five.toString())
            countryNameV_II_level_five.set("")

            countryName_level_five.set(mAllCountry.get(counter_level_five).country)
            flagUrl_level_five.set(mAllCountry.get(counter_level_five).flag)

            setMOtherCity()
            Collections.shuffle(generatedList)


            randomOne = generatedList.get(0)
            randomTwo = generatedList.get(1)
            randomThree = generatedList.get(2)
            randomFour = generatedList.get(3)
            Collections.shuffle(generatedListCountry)
            randomCounterOne = generatedListCountry.get(r.nextInt(99))
            randomCounterTwo = generatedListCountry.get(r.nextInt(99))
            randomCounterThree = generatedListCountry.get(r.nextInt(99))

            setAnswerButton()

            capitalHelp_level_five.set(mAllCountry.get(counter_level_five).country)

        } else {
            countryNameV_II_level_five.set(WRONG_ANSWER)
            wrongAnswerCounter_level_five++
        }
        if (wrongAnswerCounter_level_five == WRONG_ANSWER_COUNT) {
            countryName_level_five.set(END_GAME)
            saveResult()
            router?.goToLogo()
        }
        attemps_level_five.set((ATTEMPS - wrongAnswerCounter_level_five).toString())
        tips_level_five.set((TIPS - helpCounter_level_five).toString())
                //mAllCountry.size - 1
        if (counter_level_five == (mAllCountry.size - 1)) {
            countryName_level_five.set(WIN_LEVEL_FIVE)

            flagUrl_level_five.set("some url")

            answer_one_level_five.set("")
            answer_two_level_five.set("")
            answer_three_level_five.set("")
            answer_four_level_five.set("")

            saveResult()

            Timer().schedule(object : TimerTask() {
                override fun run() {
                    router?.goToGameResult()
                }
            }, 2000)
        }
    }

    fun genereted() {
        randomCounterOne = generatedListCountry.get(r.nextInt(99))
        randomCounterTwo = generatedListCountry.get(r.nextInt(99))
        randomCounterThree = generatedListCountry.get(r.nextInt(99))
    }

    fun setMOtherCity() {
        mOtherCountry.clear()
        mOtherCountry.add(mAllCountry.get(counter_level_five).country)
        mOtherCountry.add(mAllCountry.get(randomCounterOne).country)
        mOtherCountry.add(mAllCountry.get(randomCounterTwo).country)
        mOtherCountry.add(mAllCountry.get(randomCounterThree).country)
    }

    fun setAnswerButton() {
        answer_one_level_five.set(mOtherCountry.get(randomOne))
        answer_two_level_five.set(mOtherCountry.get(randomTwo))
        answer_three_level_five.set(mOtherCountry.get(randomThree))
        answer_four_level_five.set(mOtherCountry.get(randomFour))
    }

    fun saveResult() {
        sharedPref?.edit()?.putInt(SHARED_COUNTER_RESULT_LVL_FIVE, counter_level_five)?.apply()

    }

    fun getResult(): Int {
        val getResult: Int = sharedPref!!.getInt(SHARED_COUNTER_RESULT_LVL_FIVE, 0)
        return getResult
    }

    fun asckHelp() {
        helpCounter_level_five++
        tips_level_five.set((TIPS - helpCounter_level_five).toString())
        if (helpCounter_level_five == HELP_COUNT) {
            capitalHelp_level_five.set("Подсказок больше нет")
        }
    }

    fun setAnswerOne() {
        capitalName_level_five.set(answer_one_level_five.get())
    }

    fun setAnswerTwo() {
        capitalName_level_five.set(answer_two_level_five.get())
    }

    fun setAnswerThree() {
        capitalName_level_five.set(answer_three_level_five.get())
    }

    fun setAnswerFour() {
        capitalName_level_five.set(answer_four_level_five.get())
    }


}