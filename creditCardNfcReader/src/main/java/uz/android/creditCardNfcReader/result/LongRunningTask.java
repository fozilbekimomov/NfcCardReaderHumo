package uz.android.creditCardNfcReader.result;

import java.util.concurrent.Callable;

import uz.android.creditCardNfcReader.model.EmvCard;

public class LongRunningTask implements Callable<EmvCard> {
    @Override
    public EmvCard call() throws Exception {
        return null;
    }
}
