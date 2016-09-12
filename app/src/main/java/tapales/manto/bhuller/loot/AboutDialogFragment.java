package tapales.manto.bhuller.loot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class AboutDialogFragment extends DialogFragment{
    View v;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_about, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity()).setView(v);
        Dialog d = dialogBuilder.create();
        return d;
    }
}