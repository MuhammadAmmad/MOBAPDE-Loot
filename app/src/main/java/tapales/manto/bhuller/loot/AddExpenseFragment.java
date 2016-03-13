package tapales.manto.bhuller.loot;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class AddExpenseFragment extends Fragment{
    private DatabaseOpenHelper dbHelper;
    private int mYear, mMonth, mDay;
    private TextView categoryItem, dateText;
    private Button dateButton, submitButton, clearButton;
    private EditText inputTitle, inputValue;
    private TextInputLayout inputLayoutTitle, inputLayoutValue;
    private ImageView foodButton, leisureButton, transportButton, billButton, debtButton, othersButton;
    private static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.add_expense,container,false);
        dbHelper = new DatabaseOpenHelper(getActivity().getApplicationContext());
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        categoryItem = (TextView) v.findViewById(R.id.add_category);
        categoryItem.setText("Category - None");
        dateText = (TextView) v.findViewById(R.id.add_date);
        dateText.setText("Date - " + months[mMonth] + " " + mDay + ", " + mYear);
        inputTitle = (EditText) v.findViewById(R.id.input_expense_title);
        inputValue = (EditText) v.findViewById(R.id.input_expense_value);
        inputLayoutTitle = (TextInputLayout) v.findViewById(R.id.input_layout_title);
        inputLayoutValue = (TextInputLayout) v.findViewById(R.id.input_layout_value);
        submitButton = (Button) v.findViewById(R.id.add_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Expense expense = new Expense();
                    expense.setExpName(inputTitle.getText().toString());
                    expense.setSpentAmount(Float.valueOf(inputValue.getText().toString()));
                    expense.setCategory(categoryItem.getText().toString().replace("Category -", ""));
                    expense.setDate(dateText.getText().toString().replace("Date - ", ""));
                    //Temporary 1
                    expense.setPaymentType(1);
                    dbHelper.insertExpense(expense);
                    getActivity().setResult(Activity.RESULT_OK, new Intent(getActivity().getApplicationContext(), MainActivity.class));
                    getActivity().finish();
                }
        });
        clearButton = (Button) v.findViewById(R.id.add_clear_button);
        clearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dateText.setText("Date - " + months[mMonth] + " " + mDay + ", " + mYear);
                inputTitle.setText("");
                inputValue.setText("");
                categoryItem.setText("Category - None");
                Toast.makeText(getActivity().getApplicationContext(),"Expense Successfully Cleared", Toast.LENGTH_LONG).show();
            }
        });
        dateButton = (Button) v.findViewById(R.id.add_date_button);
        dateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog dpd = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener(){
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                                    dateText.setText("Date - " + months[monthOfYear] + " " + dayOfMonth + ", " + year);
                                    Toast.makeText(getActivity().getApplicationContext(), "Date - " + months[monthOfYear] + " " + dayOfMonth + ", " + year, Toast.LENGTH_LONG).show();
                            }}
                        , mYear, mMonth, mDay);
                dpd.show();
            }
        });
        foodButton = (ImageView) v.findViewById(R.id.add_food);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity().getApplicationContext(), "Category - Food Selected", Toast.LENGTH_LONG).show();
                categoryItem.setText("Category - Food");
            }
        });
        leisureButton = (ImageView) v.findViewById(R.id.add_leisure);
        leisureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity().getApplicationContext(),"Category - Leisure Selected", Toast.LENGTH_LONG).show();
                categoryItem.setText("Category - Leisure");
            }
        });
        transportButton = (ImageView) v.findViewById(R.id.add_transportation);
        transportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity().getApplicationContext(),"Category - Transportation Selected", Toast.LENGTH_LONG).show();
                categoryItem.setText("Category - Transportation");
            }
        });
        billButton = (ImageView) v.findViewById(R.id.add_bills);
        billButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity().getApplicationContext(),"Category - Bills Selected", Toast.LENGTH_LONG).show();
                categoryItem.setText("Category - Bills");
            }
        });
        debtButton = (ImageView) v.findViewById(R.id.add_debt);
        debtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity().getApplicationContext(),"Category - Debt Selected", Toast.LENGTH_LONG).show();
                categoryItem.setText("Category - Debt");
            }
        });
        othersButton = (ImageView) v.findViewById(R.id.add_others);
        othersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Category - Others Selected", Toast.LENGTH_LONG).show();
                categoryItem.setText("Category - Others");
            }
        });
        return v;
    }
}
