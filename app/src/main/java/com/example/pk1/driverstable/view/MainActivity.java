package com.example.pk1.driverstable.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk1.driverstable.R;
import com.example.pk1.driverstable.model.POJO.Category;
import com.example.pk1.driverstable.model.POJO.Driver;
import com.example.pk1.driverstable.model.network.APIClient;
import com.example.pk1.driverstable.presenter.MainPresenter;
import com.example.pk1.driverstable.presenter.MainPresenterImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class MainActivity extends AppCompatActivity implements MainView {
    @BindView(R.id.recyclerViewDrivers)
    RecyclerView recyclerViewDrivers;
    @BindView(R.id.buttonDeleteDriver)
    Button buttonDeleteDriver;
    @BindView(R.id.buttonSaveDriver)
    Button buttonSaveDriver;
    @BindView(R.id.autoCompleteTextViewSearchDrivers)
    AutoCompleteTextView autoCompleteTextViewSearchDrivers;
    @BindView(R.id.textViewAge)
    TextView textViewAge;
    @BindView(R.id.editTextBirthDate)
    EditText editTextBirthDate;
    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.editTextSurname)
    EditText editTextSurname;
    @BindView(R.id.editTextPatronymic)
    EditText editTextPatronymic;
    @BindView(R.id.radioButtonMen)
    RadioButton radioButtonMen;
    @BindView(R.id.radioButtonWoman)
    RadioButton radioButtonWoman;
    @BindView(R.id.spinnerCategory)
    Spinner spinnerCategory;
    List<Driver> driversLocal;
    List<Driver> drivers;
    Driver selectedDriver;
    int selected_position;
    Category category;
    boolean isPressed;
    RecyclerViewAdapter adapter;
    SimpleDateFormat simpleDateFormat;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        driversLocal = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerViewDrivers.setLayoutManager(layoutManager);
        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots("____-__-__");
        FormatWatcher formatWatcher = new MaskFormatWatcher(MaskImpl.createTerminated(slots));
        formatWatcher.installOn(editTextBirthDate);
        mainPresenter = new MainPresenterImpl(this, this, APIClient.getApi());
        autoCompleteTextViewSearchDrivers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!driversLocal.contains(drivers.get(i))) {
                    driversLocal.add(drivers.get(i));
                    adapter = new RecyclerViewAdapter(driversLocal);
                    recyclerViewDrivers.setAdapter(adapter);
                }
            }
        });
        autoCompleteTextViewSearchDrivers.addTextChangedListener(new TextWatcher() {//поиск по форуму
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {
                query = query.toString().toLowerCase();
                if (query.length() > 2) {
                    mainPresenter.searchDrivers(query.toString());
                }
            }
        });
        buttonDeleteDriver.setClickable(false);
        buttonSaveDriver.setClickable(false);
        mainPresenter.getCategory();

    }

    @OnClick(R.id.buttonDeleteDriver)
    public void deleteDriverFromRecyclerView(View view) {
        driversLocal.remove(selected_position);
        adapter.notifyItemRemoved(selected_position);
        adapter.notifyItemRangeChanged(selected_position, driversLocal.size());
        editTextName.setText("");
        editTextSurname.setText("");
        editTextPatronymic.setText("");
        editTextBirthDate.setText("");
        textViewAge.setText("");
        spinnerCategory.setSelection(0);
        radioButtonMen.setChecked(false);
        radioButtonWoman.setChecked(false);
        buttonDeleteDriver.setClickable(false);
        buttonSaveDriver.setClickable(false);

    }


    @OnClick(R.id.buttonSaveDriver)
    public void editDriver(View view) {
        try {
            Date date = simpleDateFormat.parse(editTextBirthDate.getText().toString());
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int currentYear = cal.get(Calendar.YEAR);
            cal.setTime(date);
            int birthYear = cal.get(Calendar.YEAR);
            if (currentYear - birthYear < 18) {
                Toast.makeText(MainActivity.this, "Incorrect date", Toast.LENGTH_SHORT).show();
            } else {
                Driver driver = driversLocal.get(selected_position);
                driver.setName(editTextName.getText().toString());
                driver.setSurname(editTextSurname.getText().toString());
                driver.setPatronymic(editTextPatronymic.getText().toString());
                driver.setSex(radioButtonMen.isChecked());
                driver.setCategory(category.getCategory().get(spinnerCategory.getSelectedItemPosition()));
                driver.setBirthDate(editTextBirthDate.getText().toString());
                mainPresenter.editDriver(driver);
            }
        } catch (ParseException e) {
            Toast.makeText(MainActivity.this, "Incorrect date", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void setDriverDataInView(int i) throws ParseException {
        Driver driver = driversLocal.get(i);
        Date date = simpleDateFormat.parse(driver.getBirthDate());
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int currentYear = cal.get(Calendar.YEAR);
        cal.setTime(date);
        int birthYear = cal.get(Calendar.YEAR);
        textViewAge.setText(String.valueOf(currentYear - birthYear));
        editTextName.setText(driver.getName());
        editTextSurname.setText(driver.getSurname());
        editTextPatronymic.setText(driver.getPatronymic());
        editTextBirthDate.setText(driver.getBirthDate());
        spinnerCategory.setSelection(category.getCategory().indexOf(driver.getCategory()));
        if (driver.getSex()) {
            radioButtonMen.setChecked(true);
        } else {
            radioButtonWoman.setChecked(true);
        }
    }

    @Override
    public void showMessage(String error) {
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSelectDrivers(List<Driver> drivers) {
        this.drivers = drivers;
        String[] array = new String[drivers.size()];
        for (int i = 0; i < drivers.size(); i++) {
            array[i] = drivers.get(i).getSurname() + " " + drivers.get(i).getName() + " " + drivers.get(i).getPatronymic();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autoCompleteTextViewSearchDrivers.setAdapter(adapter);
    }

    @Override
    public void updateDataDriverView() {
        adapter.notifyItemChanged(selected_position);
        try {
            setDriverDataInView(selected_position);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "An error load birth date driver", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showCategory(Category category) {
        this.category = category;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, category.getCategory().toArray(new String[category.getCategory().size()]));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    class RecyclerViewHolders extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.textViewBirthDate)
        TextView textViewBirthDate;
        @BindView(R.id.textViewSex)
        TextView textViewSex;
        @BindView(R.id.textViewCategory)
        TextView textViewCategory;
        RecyclerViewHolders(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
        private List<Driver> itemList;

        RecyclerViewAdapter(List<Driver> itemList) {
            this.itemList = itemList;
        }

        @Override
        public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
            return new RecyclerViewHolders(layoutView);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
            holder.title.setText(itemList.get(position).getSurname() + " " + itemList.get(position).getName() + " " + itemList.get(position).getPatronymic());
            holder.textViewBirthDate.setText(itemList.get(position).getBirthDate());
            if (itemList.get(position).getSex()) {
                holder.textViewSex.setText("M");
            } else {
                holder.textViewSex.setText("W");
            }
            holder.textViewCategory.setText(itemList.get(position).getCategory());
            holder.itemView.setBackgroundColor(Color.WHITE);
            if (isPressed) {
                if (selected_position == position) {
                    holder.itemView.setBackgroundColor(Color.parseColor("#337536"));
                } else {
                    holder.itemView.setBackgroundColor(Color.WHITE);
                }
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isPressed = true;
                    notifyItemChanged(selected_position);
                    selected_position = position;
                    selectedDriver = driversLocal.get(position);
                    notifyItemChanged(selected_position);
                    buttonDeleteDriver.setClickable(true);
                    buttonSaveDriver.setClickable(true);
                    try {
                        setDriverDataInView(selected_position);
                    } catch (ParseException e) {
                        Toast.makeText(MainActivity.this, "An error load birth date driver", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return this.itemList.size();
        }
    }
}
