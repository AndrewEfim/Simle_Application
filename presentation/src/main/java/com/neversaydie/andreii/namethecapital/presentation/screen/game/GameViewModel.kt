package com.neversaydie.andreii.namethecapital.presentation.screen.game

import android.content.SharedPreferences
import android.databinding.ObservableField
import android.preference.PreferenceManager
import android.util.Log
import com.neversaydie.andreii.domain.entity.Country
import com.neversaydie.andreii.namethecapital.presentation.app.App
import com.neversaydie.andreii.namethecapital.presentation.base.BaseViewModel
import com.neversaydie.andreii.namethecapital.presentation.factories.UseCaseProvider
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import io.reactivex.rxkotlin.subscribeBy


class GameViewModel : BaseViewModel<GameRouter>() {

    private val HELP_COUNT: Int = 4
    private val WRONG_ANSWER_COUNT: Int = 4
    private val END_GAME: String = "Вы проиграли !"
    private val WRONG_ANSWER: String = "Ответ не верный"
    private val WIN_GAME: String = "Вы выиграли"
    private val SHARED_COUNTER_RESULT = "GAME_RESULT"

    var sharedPref: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(App.instance)

    private val mCountry: MutableList<Country> = mutableListOf()
    private val mCounter: MutableList<Int> = ArrayList()
    private val getCountryUseCase = UseCaseProvider.provideCountryListUseCase()
    var score = ObservableField<String>("")
    var counter = 0
    var helpCounter = 0
    var wrongAnswerCounter = 0
    var countryName = ObservableField<String>("")
    var capitalName = ObservableField<String>("")
    var capitalHelp = ObservableField<String>("")
    fun setCountry(country: String) {
        countryName.set(country)
    }

    init {


        addToDisposable(getCountryUseCase.get()
                .subscribeBy(onNext = {
                    mCountry.addAll(it)
                    countryName.set(mCountry.get(counter).country)
                    Log.d("myLog", "mCountry" + mCountry.size.toString())
                    Log.d("myLog", "counter" + counter.toString())
                }))
        // countryName.set(mCountry.get(counter).country)
    }


    fun checkAnswer() {
        capitalHelp.set(mCountry.get(counter).capital)//

        if ((mCountry.get(counter).capital).equals(capitalName.get(), ignoreCase = true)) {
            counter++
            mCounter.add(counter)
            score.set(counter.toString())
            capitalName.set("")

            countryName.set(mCountry.get(counter).country)
            capitalHelp.set(mCountry.get(counter).capital)

            if (counter == mCountry.size) {
                countryName.set(WIN_GAME)
            }

            Log.d("myLog", "answer is correct")
            Log.d("myLog", "checkAnswer" + countryName.get() + "..." + capitalName.get())
            Log.d("myLog", "checkAnswer")
            Log.d("myLog", "counter" + counter.toString() + "..." + mCounter.size)

        } else {
            countryName.set(WRONG_ANSWER)
            wrongAnswerCounter++

            Log.d("myLog", "answer is not correct")
            Log.d("myLog", "checkAnswer" + countryName.get() + "..." + capitalName.get())
            Log.d("myLog", "checkAnswer")
            Log.d("myLog", "counter" + counter.toString() + "..." + mCounter.size)
        }
        if (wrongAnswerCounter == WRONG_ANSWER_COUNT) {
            countryName.set(END_GAME)
            saveResult()
            router?.goToLogo()
        }


    }

    fun saveResult() {
        sharedPref?.edit()?.putInt(SHARED_COUNTER_RESULT, counter)?.apply()

    }


    fun asckHelp() {
        helpCounter++
        if (helpCounter == HELP_COUNT) {
            capitalHelp.set("Подсказок больше нет")

        }


    }


    fun gameStart(capital: String) {

        addToDisposable(getCountryUseCase.get()
                .subscribeBy(onNext = {
                    mCountry.addAll(it)

                    setCountry(it.get(counter).country)


                    countryName.set(mCountry.get(counter).country)

                    if (mCountry.get(counter).capital.equals(capital)) {
                        counter++
                    } else {//FIXME
                    }
                }

                ))

    }
}