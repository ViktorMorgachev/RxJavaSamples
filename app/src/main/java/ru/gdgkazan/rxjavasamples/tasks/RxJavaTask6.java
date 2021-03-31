package ru.gdgkazan.rxjavasamples.tasks;

import android.util.Log;

import androidx.annotation.NonNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public class RxJavaTask6 {

    /**
     * TODO :
     * <p>
     * Create the stream of integers [1..100000] and apply next functions:
     * 1) Multiply all elements by 2
     * 2) Remove 40 000 elements from start and 40 000 elements from end
     * 3) Remove all values which is not divided by 3
     * 4) Find multiplication of all values in the stream and transform into one single BigInteger
     * 5) Operations above are rather slow. Try to calculate result in observable only once.
     * You code will be also tested for speed - you shouldn't recalculate result for each new subscriber.
     */
    @NonNull
    public static Observable<BigInteger> task6Observable() {
        ArrayList<BigInteger> list = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            list.add(new BigInteger(String.valueOf(i)));
        }
        return Observable.from(list)
                .observeOn(Schedulers.io())
                .map(i -> i.multiply(BigInteger.valueOf(2)))
                .filter(value -> value.intValue() > 40_000 * 2 && value.intValue() < ((100_000 * 2) - 40_000))
                .filter(value -> value.divide(new BigInteger("3")).equals(new BigInteger("3")));
    }

}