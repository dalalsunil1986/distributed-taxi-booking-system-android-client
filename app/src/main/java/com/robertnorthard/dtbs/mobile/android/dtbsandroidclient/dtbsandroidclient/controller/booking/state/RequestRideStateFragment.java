package com.robertnorthard.dtbs.mobile.android.dtbsandroidclient.dtbsandroidclient.controller.booking.state;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.robertnorthard.dtbs.mobile.android.dtbsandroidclient.R;
import com.robertnorthard.dtbs.mobile.android.dtbsandroidclient.dtbsandroidclient.service.config.DtbsPreferences;


/**
 * Represents a request ride booking state.
 */
public class RequestRideStateFragment extends Fragment implements BookingState {

    private Button btnBookTaxi;
    private EditText txtPickupLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_request_ride, container, false);

        txtPickupLocation = (EditText) getActivity().findViewById(R.id.txt_pickup_location);
        btnBookTaxi = (Button)v.findViewById(R.id.btn_request_ride1);

        btnBookTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestTaxi();
            }
        });

        return v;
    }

    @Override
    public void awaitTaxi() {
        throw new IllegalStateException("Taxi not requested.");
    }

    @Override
    public void requestTaxi() {

        Fragment bookingFragment = new BookingFragment();

        //bundle required data.
        Bundle data = new Bundle();
        data.putString(DtbsPreferences.DATA_PICKUP_LOCATION, txtPickupLocation.getText().toString());
        bookingFragment.setArguments(data);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, bookingFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void pickupPassenger() {
        throw new IllegalStateException("Taxi not requested.");
    }

    @Override
    public void dropOffPassenger() {
        throw new IllegalStateException("Passenger not picked up.");
    }

    @Override
    public void cancelBooking() {
        throw new IllegalStateException("Not tracking a booking.");
    }
}