/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.hardware.location;

import android.annotation.NonNull;
import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

import libcore.util.EmptyArray;

/**
 * Describes an instance of a nanoapp, used by the internal state managed by ContextHubService.
 *
 * TODO(b/69270990) Remove this class once the old API is deprecated.
 *
 * @deprecated Use {@link android.hardware.location.NanoAppState} instead.
 *
 * @hide
 */
@SystemApi
@Deprecated
public class NanoAppInstanceInfo implements Parcelable {
    private String mPublisher = "Unknown";
    private String mName = "Unknown";

    private int mHandle;
    private long mAppId;
    private int mAppVersion;
    private int mContexthubId;

    private int mNeededReadMemBytes = 0;
    private int mNeededWriteMemBytes = 0;
    private int mNeededExecMemBytes = 0;

    private int[] mNeededSensors = EmptyArray.INT;
    private int[] mOutputEvents = EmptyArray.INT;

    public NanoAppInstanceInfo() {
    }

    /**
     * @hide
     */
    public NanoAppInstanceInfo(int handle, long appId, int appVersion, int contextHubId) {
        mHandle = handle;
        mAppId = appId;
        mAppVersion = appVersion;
        mContexthubId = contextHubId;
    }

    /**
     * get the publisher of this app
     *
     * @return String - name of the publisher
     */
    public String getPublisher() {
        return mPublisher;
    }

    /**
     * get the name of the app
     *
     * @return String - name of the app
     */
    public String getName() {
        return mName;
    }

    /**
     * Get the application identifier
     *
     * @return int - application identifier
     */
    public long getAppId() {
        return mAppId;
    }

    /**
     * Get the application version
     *
     * @return int - version of the app
     */
    public int getAppVersion() {
        return mAppVersion;
    }

    /**
     * Get the read memory needed by the app
     *
     * @return int - readable memory needed in bytes
     */
    public int getNeededReadMemBytes() {
        return mNeededReadMemBytes;
    }

    /**
     *  get writable memory needed by the app
     *
     * @return int - writable memory needed by the app
     */
    public int getNeededWriteMemBytes() {
        return mNeededWriteMemBytes;
    }

    /**
     * get executable memory needed by the app
     *
     * @return int - executable memory needed by the app
     */
    public int getNeededExecMemBytes() {
        return mNeededExecMemBytes;
    }

    /**
     * Get the sensors needed by this app
     *
     * @return int[] all the required sensors needed by this app
     */
    @NonNull
    public int[] getNeededSensors() {
        return mNeededSensors;
    }

    /**
     * get the events generated by this app
     *
     * @return all the events that can be generated by this app
     */
    @NonNull
    public int[] getOutputEvents() {
        return mOutputEvents;
    }

    /**
     * get the context hub identifier
     *
     * @return int - system unique hub identifier
     */
    public int getContexthubId() {
        return mContexthubId;
    }

    /**
     * get a handle to the nano app instance
     *
     * @return int - handle to this instance
     */
    public int getHandle() {
        return mHandle;
    }

    private NanoAppInstanceInfo(Parcel in) {
        mPublisher = in.readString();
        mName = in.readString();

        mHandle = in.readInt();
        mAppId = in.readLong();
        mAppVersion = in.readInt();
        mContexthubId = in.readInt();
        mNeededReadMemBytes = in.readInt();
        mNeededWriteMemBytes = in.readInt();
        mNeededExecMemBytes = in.readInt();

        int neededSensorsLength = in.readInt();
        mNeededSensors = new int[neededSensorsLength];
        in.readIntArray(mNeededSensors);

        int outputEventsLength = in.readInt();
        mOutputEvents = new int[outputEventsLength];
        in.readIntArray(mOutputEvents);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mPublisher);
        out.writeString(mName);

        out.writeInt(mHandle);
        out.writeLong(mAppId);
        out.writeInt(mAppVersion);
        out.writeInt(mContexthubId);
        out.writeInt(mNeededReadMemBytes);
        out.writeInt(mNeededWriteMemBytes);
        out.writeInt(mNeededExecMemBytes);

        // arrays are never null
        out.writeInt(mNeededSensors.length);
        out.writeIntArray(mNeededSensors);
        out.writeInt(mOutputEvents.length);
        out.writeIntArray(mOutputEvents);
    }

    public static final @android.annotation.NonNull Parcelable.Creator<NanoAppInstanceInfo> CREATOR
            = new Parcelable.Creator<NanoAppInstanceInfo>() {
        public NanoAppInstanceInfo createFromParcel(Parcel in) {
            return new NanoAppInstanceInfo(in);
        }

        public NanoAppInstanceInfo[] newArray(int size) {
            return new NanoAppInstanceInfo[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        String retVal = "handle : " + mHandle;
        retVal += ", Id : 0x" + Long.toHexString(mAppId);
        retVal += ", Version : 0x" + Integer.toHexString(mAppVersion);

        return retVal;
    }
}
