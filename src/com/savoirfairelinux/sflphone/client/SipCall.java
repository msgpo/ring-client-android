/*
 *  Copyright (C) 2004-2012 Savoir-Faire Linux Inc.
 *
 *  Author: Alexandre Savard <alexandre.savard@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *   Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 *  Additional permission under GNU GPL version 3 section 7:
 *
 *  If you modify this program, or any covered work, by linking or
 *  combining it with the OpenSSL project's OpenSSL library (or a
 *  modified version of that library), containing parts covered by the
 *  terms of the OpenSSL or SSLeay licenses, Savoir-Faire Linux Inc.
 *  grants you additional permission to convey the resulting work.
 *  Corresponding Source for a non-source form of such a combination
 *  shall include the source code for the parts of OpenSSL used as well
 *  as that of the covered work.
 */
package com.savoirfairelinux.sflphone.client;

import android.os.Parcelable;
import android.util.Log;
import java.util.ArrayList;

public class SipCall //implements Parcelable
{
    final static String TAG = "SipCall";
    public static CallElementList mCallElementList;
    public CallInfo mCallInfo;

    public enum CallState {
        INVALID,      // The call is not existent in SFLphone service
        NULL,         // Before any action performed
        CALLING,      // After INVITE is sent
        INCOMING,     // After INVITE is received
        EARLY,        // After response with To tag
        CONNECTING,   // After 2xx is sent/received
        CONFIRMED,    // After ACK is sent/received
        DISCONNECTED  // Session is terminated
    }

    public enum MediaState {
        NONE,        // No media currently
        ACTIVE,      // Media is active
        LOCAL_HOLD,  // Media is put on hold bu user
        REMOTE_HOLD, // Media is put on hold by peer
        ERROR,       // Media is in error state
    }

    public static class CallInfo
    {
        public String mCallID = "";
        public String mDisplayName = "";
        public String mPhone = "";
        public String mEmail = "";
        public String mRemoteContact = "";
        public CallState mCallState = CallState.NULL;
        public MediaState mMediaState = MediaState.NONE;
    }

    public SipCall()
    {
        mCallInfo = new CallInfo(); 
    }

    public SipCall(CallInfo info)
    {
        mCallInfo = info; 
    }

    public static void setCallElementList(CallElementList list)
    {
        mCallElementList = list;
    }

    /** TODO factory method that should be moved in CallList 
    public static SipCall getCallInstance(CallInfo info)
    {
        if(CallList.isEmpty())
            return new SipCall(info);
       
        for(SipCall sipcall : CallList) {
            if(sipcall.mCallInfo.mDisplayName.equals(info.mDisplayName)) {
                if(sipcall.mCallInfo.mPhone.equals(info.mPhone)) {
                    return sipcall;
                }
            }
        }

        return new SipCall(info);
    }
    */

    public void placeCall()
    {
        mCallElementList.addCall(this); 
        // mManager.callmanagerJNI.placeCall("IP2IP", "CALL1234", "192.168.40.35");
    }

    public void answer()
    {

    }

    public void hangup()
    {
        mCallElementList.removeCall(this);
        // mManager.callmanagerJNI.hangup("IP2IP", "CALL1234", "192.168.40.35");
    }

    public void addToConference()
    {
        Log.i(TAG, "Add call to conference");
    }

    public void sendTextMessage()
    {
        Log.i(TAG, "Send text message");
    }
}
