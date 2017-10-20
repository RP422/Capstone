package com.capstone.mike.a3_in_1flightmanager.logbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mike on 10/11/2017.
 */

public class DBHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "logbookInfo";
    private static final String TABLE_FLIGHT_LOG = "flightLog";

    private static final String COLUMN_ID                   = "ID";
    private static final String COLUMN_DATE                 = "FLIGHT_DATE";
    private static final String COLUMN_AIRCRAFT_MODEL       = "AIRCRAFT_MODEL";
    private static final String COLUMN_AIRCRAFT_IDENT       = "AIRCRAFT_IDENT";
    private static final String COLUMN_DEPARTURE_LOC        = "DEPARTURE_LOC";
    private static final String COLUMN_ARRIVAL_LOC          = "ARRIVAL_LOC";
    private static final String COLUMN_NR_INST_APP          = "NR_INST_APP";
    private static final String COLUMN_RMKS_AND_ENDSMTS     = "RMKS_AND_ENDSMTS";
    private static final String COLUMN_NR_DAY_LDG           = "NR_DAY_LDG";
    private static final String COLUMN_NR_NGT_LDG           = "NR_NGT_LDG";
    private static final String COLUMN_AIRCRAFT_CATEGORY    = "AIRCRAFT_CATEGORY";
    private static final String COLUMN_CATEGORY_FLIGHT_TIME = "CATEGORY_FLIGHT_TIME";
    private static final String COLUMN_AIRCRAFT_CLASS       = "AIRCRAFT_CLASS";
    private static final String COLUMN_CLASS_FLIGHT_TIME    = "CLASS_FLIGHT_TIME";
    private static final String COLUMN_NGT_TIME             = "NGT_TIME";
    private static final String COLUMN_ACTL_INST_TIME       = "ACTL_INST_TIME";
    private static final String COLUMN_SIM_INST_TIME        = "SIM_INST_TIME";
    private static final String COLUMN_FLGT_SIM_TIME        = "FLGT_SIM_TIME";
    private static final String COLUMN_XCOUNTRY_TIME        = "XCOUNTRY_TIME";
    private static final String COLUMN_AS_FLGT_INSTRUCT     = "AS_FLGT_INSTRUCT";
    private static final String COLUMN_DUAL_RECIVED         = "DUAL_RECIVED";
    private static final String COLUMN_PIC_TIME             = "PIC_TIME";


    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // First creation of the Table
        String createStatement = "CREATE TABLE " + TABLE_FLIGHT_LOG + "(" +
                COLUMN_ID                   + " INTEGER PRIMARY KEY, " +
                COLUMN_DATE                 + " VARCHAR(10), " + // YYYY-MM-DD
                COLUMN_AIRCRAFT_MODEL + " VARCHAR(50), " +
                COLUMN_AIRCRAFT_IDENT       + " VARCHAR(15), " +
                COLUMN_DEPARTURE_LOC        + " VARCHAR(15), " +
                COLUMN_ARRIVAL_LOC          + " VARCHAR(15), " +
                COLUMN_NR_INST_APP          + " INT, " +
                COLUMN_RMKS_AND_ENDSMTS     + " VARCHAR(1000), " +
                COLUMN_NR_DAY_LDG           + " INT, " +
                COLUMN_NR_NGT_LDG           + " INT, " +
                COLUMN_AIRCRAFT_CATEGORY    + " VARCHAR(50), " +
                COLUMN_CATEGORY_FLIGHT_TIME + " FLOAT, " +
                COLUMN_AIRCRAFT_CLASS       + " VARCHAR(50), " +
                COLUMN_CLASS_FLIGHT_TIME    + " FLOAT, " +
                COLUMN_NGT_TIME             + " FLOAT, " +
                COLUMN_ACTL_INST_TIME       + " FLOAT, " +
                COLUMN_SIM_INST_TIME        + " FLOAT, " +
                COLUMN_FLGT_SIM_TIME        + " FLOAT, " +
                COLUMN_XCOUNTRY_TIME        + " FLOAT, " +
                COLUMN_AS_FLGT_INSTRUCT     + " FLOAT, " +
                COLUMN_DUAL_RECIVED         + " FLOAT, " +
                COLUMN_PIC_TIME             + " FLOAT";

        db.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        // Update table from older version
            // Unnecessary step on DB Ver 1, as this is the first version.
        return;
    }

    public float getHoursFlown(int numDays)
    {
        throw new UnsupportedOperationException();
    }
    public float getHoursFlown(int numDays, String category)
    {
        throw new UnsupportedOperationException();
    }
    // getHoursFlown for numdays and class
    // getHoursFlown for numdays and the special conditions listed in the last 9 columns
    // getHoursFlown for numdays and the specific aircraft (type and/or ID?)
    // getHoursFlown like the above methods, but with a date range instead of the last X number of days

    // Retrieve an entry or entries via date
    // Retrieve flights via departure or arrival locations
    // getNumDayLandings / getNumNightLandings methods
    // // the split between day and night is important for that second one.
    // Retrieve flights based on if hours were logged under the special conditions
}
