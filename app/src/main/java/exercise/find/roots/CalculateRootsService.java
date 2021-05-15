package exercise.find.roots;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CalculateRootsService extends IntentService {


  public CalculateRootsService() {
    super("CalculateRootsService");
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    if (intent == null) return;
    long timeStartMs = System.currentTimeMillis();
    long numberToCalculateRootsFor = intent.getLongExtra("number_for_service", 0);
    if (numberToCalculateRootsFor <= 0) {
      Log.e("CalculateRootsService", "can't calculate roots for non-positive input" + numberToCalculateRootsFor);
      return;
    }

    /*
    TODO:
     calculate the roots.
     check the time (using `System.currentTimeMillis()`) and stop calculations if can't find an answer after 20 seconds
     upon success (found a root, or found that the input number is prime):
      send broadcast with action "found_roots" and with extras:
       - "original_number"(long)
       - "root1"(long)
       - "root2"(long)
     upon failure (giving up after 20 seconds without an answer):
      send broadcast with action "stopped_calculations" and with extras:
       - "original_number"(long)
       - "time_until_give_up_seconds"(long) the time we tried calculating

      examples:
       for input "33", roots are (3, 11)
       for input "30", roots can be (3, 10) or (2, 15) or other options
       for input "17", roots are (17, 1)
       for input "829851628752296034247307144300617649465159", after 20 seconds give up

     */
    long root1;
    long root2;
    long sqrt_of_number = (long)Math.sqrt(numberToCalculateRootsFor);
    for (long i = sqrt_of_number; i > 0; i--) {
      if(numberToCalculateRootsFor % i == 0) {
        root1 = i;
        root2 = numberToCalculateRootsFor/ i;
        // todo send broadcast
        Intent roots_intent = new Intent();
        roots_intent.setAction("found_roots");
        roots_intent.putExtra("original_number", numberToCalculateRootsFor);
        roots_intent.putExtra("root1", root1);
        roots_intent.putExtra("root2", root2);
        roots_intent.putExtra("time", (System.currentTimeMillis() - timeStartMs)/1000L);
        sendBroadcast(roots_intent);
        return;

      }
      if(System.currentTimeMillis() - timeStartMs > 20000L){
        // Todo send broadcast with error.
        Intent error_intent = new Intent("stopped_calculations");
        error_intent.putExtra("original_number", numberToCalculateRootsFor);
        error_intent.putExtra("time_until_give_up_seconds", (System.currentTimeMillis() - timeStartMs)/1000L);
        sendBroadcast(error_intent);
        return;
      }
    }
    while(true){
      if(System.currentTimeMillis() - timeStartMs > 20000L) {
        // Todo send broadcast with error.
        Intent error_intent = new Intent("stopped_calculations");
        error_intent.putExtra("original_number", numberToCalculateRootsFor);
        error_intent.putExtra("time_until_give_up_seconds", (System.currentTimeMillis() - timeStartMs) / 1000L);
        sendBroadcast(error_intent);
        return;
      }
    }
  }
}