package com.example.firebaseapp2;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.app.AlertDialog;

        import android.text.TextUtils;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;

        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import java.util.ArrayList;
        import java.util.List;

public class Admin_Select extends AppCompatActivity {

    EditText editTextLocation;
    EditText editTextIntensity;

    CheckBox TimeTrial;
    CheckBox HillClimb;
    CheckBox RoadRace;
    CheckBox GroupRides;
    Button buttonAddProduct;
    ListView listViewProducts;

    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

        editTextLocation = (EditText) findViewById(R.id.editTextName2);
        editTextIntensity = (EditText) findViewById(R.id.editTextName3);
        listViewProducts = (ListView) findViewById(R.id.listViewProducts);
        buttonAddProduct = (Button) findViewById(R.id.addButton);
        TimeTrial = (CheckBox) findViewById(R.id.checkBox);
        HillClimb = (CheckBox) findViewById(R.id.checkBox2);
        RoadRace = (CheckBox) findViewById(R.id.checkBox4);
        GroupRides = (CheckBox) findViewById(R.id.checkBox5);

        products = new ArrayList<>();

        //adding an onclicklistener to button
        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });

        listViewProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = products.get(i);
                showUpdateDeleteDialog(product.getId(), product.getProductName());
                return true;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    private void showUpdateDeleteDialog(final String productId, String productName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        //final EditText editTextLocation = (EditText) dialogView.findViewById(R.id.editTextName2);
        //final EditText editTextIntensity  = (EditText) dialogView.findViewById(R.id.editTextName3);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateProduct);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteProduct);

        dialogBuilder.setTitle(productName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = editTextLocation.getText().toString().trim();
                double intensity = Double.parseDouble(String.valueOf(editTextIntensity.getText().toString()));
                if (!TextUtils.isEmpty(location)) {
                    //FIX
                    //updateProduct(productId, location, intensity);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProduct(productId);
                b.dismiss();
            }
        });
    }

    private void updateProduct(String id, String name, double intensity, String location) {
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("products").child(id);

        // FIX
        //Product p = new Product(id, name, intensity, location);
        //dr.setValue(p);

        Toast.makeText(getApplicationContext(), "Product Updated", Toast.LENGTH_LONG).show();
    }

    private void deleteProduct(String id) {
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("products").child(id);

        dr.removeValue();
        Toast.makeText(getApplicationContext(), "Product Deleted", Toast.LENGTH_LONG).show();
    }


    private void addProduct() {
        TimeTrial = (CheckBox) findViewById(R.id.checkBox);
        HillClimb = (CheckBox) findViewById(R.id.checkBox2);
        RoadRace = (CheckBox) findViewById(R.id.checkBox4);
        GroupRides = (CheckBox) findViewById(R.id.checkBox5);
        String name;
        if (GroupRides.isChecked()) {
            name = "GroupRides";
        }
        else if ((RoadRace).isChecked()) {
            name = "RoadRace";
        }
        else if (HillClimb.isChecked()) {
            name = "HillClimb";
        }
        else if (TimeTrial.isChecked()) {
            name = "TimeTrial";
        }
        else {
            name = "ERROR activity not selected";
        }

        double Level = Double.parseDouble(String.valueOf(editTextIntensity.getText().toString()));
        String Location = String.valueOf(editTextLocation.getText().toString());

        if (!TextUtils.isEmpty(name)) {
            //FIX
            //String id = databaseProducts.push().getKey();
            //Product p = new Product(id, name, level, location);

            //databaseProducts.child(id).setValue(p);

            editTextIntensity.setText("");
            editTextLocation.setText("");

            Toast.makeText(this, "Product added", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }


        Toast.makeText(this, "NOT IMPLEMENTED YET", Toast.LENGTH_LONG).show();
    }
}
