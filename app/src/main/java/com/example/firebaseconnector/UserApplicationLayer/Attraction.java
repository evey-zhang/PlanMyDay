package com.example.firebaseconnector.UserApplicationLayer;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Attraction implements Parcelable {
	private String id;
	private String name;

	private String address;
	private String longitude;
	private String latitude;
	private String openTime;
	private String closeTime;
	private String description;

	public Attraction() {
	}

    public Attraction(String id, String name, String address, String longitude, String latitude, String openTime, String closeTime, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.description = description;
    }

    public Attraction(String name, String address, String openTime, String closeTime) {
        this.name = name;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

	protected Attraction(Parcel in) {
		id = in.readString();
		name = in.readString();
		address = in.readString();
		longitude = in.readString();
		latitude = in.readString();
		openTime = in.readString();
		closeTime = in.readString();
		description = in.readString();
	}

	public static final Creator<Attraction> CREATOR = new Creator<Attraction>() {
		@Override
		public Attraction createFromParcel(Parcel in) {
			return new Attraction(in);
		}

		@Override
		public Attraction[] newArray(int size) {
			return new Attraction[size];
		}
	};

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel parcel, int i) {
		parcel.writeString(id);
		parcel.writeString(name);
		parcel.writeString(address);
		parcel.writeString(longitude);
		parcel.writeString(latitude);
		parcel.writeString(openTime);
		parcel.writeString(closeTime);
		parcel.writeString(description);
	}
}
