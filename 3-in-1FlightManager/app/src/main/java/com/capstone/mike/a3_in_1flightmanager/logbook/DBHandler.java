package com.capstone.mike.a3_in_1flightmanager.logbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String createStatement = // Prepare yourself, this is gonna be beautiful
                "CREATE TABLE "             +     TABLE_FLIGHT_LOG        + "(" +
                COLUMN_ID                   +  " INTEGER PRIMARY KEY, "   +
                COLUMN_DATE                 +      " VARCHAR(10), "       + // YYYY-MM-DD
                COLUMN_AIRCRAFT_MODEL       +      " VARCHAR(50), "       +
                COLUMN_AIRCRAFT_IDENT       +      " VARCHAR(15), "       +
                COLUMN_DEPARTURE_LOC        +      " VARCHAR(15), "       +
                COLUMN_ARRIVAL_LOC          +      " VARCHAR(15), "       +
                COLUMN_NR_INST_APP          +          " INT, "           +
                COLUMN_RMKS_AND_ENDSMTS     +     " VARCHAR(1000), "      +
                COLUMN_NR_DAY_LDG           +          " INT, "           +
                COLUMN_NR_NGT_LDG           +          " INT, "           +
                COLUMN_AIRCRAFT_CATEGORY    +      " VARCHAR(50), "       +
                COLUMN_CATEGORY_FLIGHT_TIME +         " FLOAT, "          +
                COLUMN_AIRCRAFT_CLASS       +      " VARCHAR(50), "       +
                COLUMN_CLASS_FLIGHT_TIME    +         " FLOAT, "          +
                COLUMN_NGT_TIME             +         " FLOAT, "          +
                COLUMN_ACTL_INST_TIME       +         " FLOAT, "          +
                COLUMN_SIM_INST_TIME        +         " FLOAT, "          +
                COLUMN_FLGT_SIM_TIME        +         " FLOAT, "          +
                COLUMN_XCOUNTRY_TIME        +         " FLOAT, "          +
                COLUMN_AS_FLGT_INSTRUCT     +         " FLOAT, "          +
                COLUMN_DUAL_RECIVED         +         " FLOAT, "          +
                COLUMN_PIC_TIME             +          " FLOAT"           ;

        db.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        // Update table from older version
            // Unnecessary step on DB Ver 1, as this is the only version right now.
        return;
    }

    public LogbookEntry[] getAll()
    {
        String query = "SELECT * " +
                       "FROM " + TABLE_FLIGHT_LOG;

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    public LogbookEntry[] getAll(int numDays)
    {
        String query = "SELECT * " +
                       "FROM " + TABLE_FLIGHT_LOG + " ";

        if(numDays > 1)
        {
            query += "WHERE " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAYS')";
        }
        else
        {
            query += "WHERE " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAY')";
        }

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    public float getHoursFlown(int numDays)
    {
        String query = "SELECT SUM(" + COLUMN_CLASS_FLIGHT_TIME + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " ";

        if(numDays > 1)
        {
            query += "WHERE " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAYS')";
        }
        else
        {
            query += "WHERE " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAY')";
        }

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }

    public float getTotalHoursFlown(AircraftCategory category)
    {
        String query = "SELECT SUM(" + COLUMN_CLASS_FLIGHT_TIME + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_AIRCRAFT_CATEGORY + " = " + category;

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    public float getHoursFlown(int numDays, AircraftCategory category)
    {
        String query = "SELECT SUM(" + COLUMN_CLASS_FLIGHT_TIME + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_AIRCRAFT_CATEGORY + " = " + category.toString();

        if(numDays > 1)
        {
            query += " AND " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAYS')";
        }
        else
        {
            query += " AND " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAY')";
        }

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }

    public float getTotalHoursFlown(AircraftClass aircraftClass)
    {
        String query = "SELECT SUM(" + COLUMN_CLASS_FLIGHT_TIME + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_AIRCRAFT_CATEGORY + " = " + aircraftClass;

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    public float getHoursFlown(int numDays, AircraftClass aircraftClass)
    {
        String query = "SELECT SUM(" + COLUMN_CLASS_FLIGHT_TIME + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_AIRCRAFT_CATEGORY + " = " + aircraftClass;

        if(numDays > 1)
        {
            query += " AND " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAYS')";
        }
        else
        {
            query += " AND " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAY')";
        }

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }

    public float getTotalHoursFlown(SpecialConditions conditions)
    {
        String requestedColumn;

        switch(conditions)
        {
            case NIGHT_FLYING:
                requestedColumn = COLUMN_NGT_TIME;
                break;
            case ACUTAL_INSTRUMENT:
                requestedColumn = COLUMN_ACTL_INST_TIME;
                break;
            case SIMULATED_INSTRUMENT:
                requestedColumn = COLUMN_SIM_INST_TIME;
                break;
            case FLIGHT_SIMULATOR:
                requestedColumn = COLUMN_FLGT_SIM_TIME;
                break;
            case CROSS_COUNTRY:
                requestedColumn = COLUMN_XCOUNTRY_TIME;
                break;
            case AS_FLIGHT_INSTRUCTOR:
                requestedColumn = COLUMN_AS_FLGT_INSTRUCT;
                break;
            case DUAL_RECIEVED:
                requestedColumn = COLUMN_DUAL_RECIVED;
                break;
            case PILOT_IN_COMMAND:
                requestedColumn = COLUMN_PIC_TIME;
                break;
            default:
                throw new UnsupportedOperationException("That condition option is not supported yet.");
        }

        String query = "SELECT SUM(" + requestedColumn + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " ";

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    public float getHoursFlown(int numDays, SpecialConditions conditions)
    {
        String requestedColumn;

        switch(conditions)
        {
            case NIGHT_FLYING:
                requestedColumn = COLUMN_NGT_TIME;
                break;
            case ACUTAL_INSTRUMENT:
                requestedColumn = COLUMN_ACTL_INST_TIME;
                break;
            case SIMULATED_INSTRUMENT:
                requestedColumn = COLUMN_SIM_INST_TIME;
                break;
            case FLIGHT_SIMULATOR:
                requestedColumn = COLUMN_FLGT_SIM_TIME;
                break;
            case CROSS_COUNTRY:
                requestedColumn = COLUMN_XCOUNTRY_TIME;
                break;
            case AS_FLIGHT_INSTRUCTOR:
                requestedColumn = COLUMN_AS_FLGT_INSTRUCT;
                break;
            case DUAL_RECIEVED:
                requestedColumn = COLUMN_DUAL_RECIVED;
                break;
            case PILOT_IN_COMMAND:
                requestedColumn = COLUMN_PIC_TIME;
                break;
            default:
                throw new UnsupportedOperationException("That condition option is not supported yet.");
        }

        String query = "SELECT SUM(" + requestedColumn + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " ";

        if(numDays > 1)
        {
            query += "WHERE " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAYS')";
        }
        else
        {
            query += "WHERE " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAY')";
        }

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }

    public float getTotalHoursFlown(String id)
    {
        String query = "SELECT SUM(" + COLUMN_CLASS_FLIGHT_TIME + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_AIRCRAFT_IDENT + " = " + id;

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    public float getHoursFlown(int numDays, String id)
    {
        String query = "SELECT SUM(" + COLUMN_CLASS_FLIGHT_TIME + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_AIRCRAFT_IDENT + " = " + id;

        if(numDays > 1)
        {
            query += " AND " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAYS')";
        }
        else
        {
            query += " AND " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAY')";
        }

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    // getHoursFlown like the above methods, but with a date range instead of the last X number of days

    // Retrieve an entry or entries via date
    public LogbookEntry[] getEntries(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        String query = "SELECT * " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE STRFTIME('%y-%m-%d'" + COLUMN_DATE + ") = " + format.format(date);

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    // Retrieve flights via departure or arrival locations
    public LogbookEntry[] getFlightsTo(String airportID)
    {
        String query = "SELECT * " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_ARRIVAL_LOC + " = " + airportID;

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    public LogbookEntry[] getFlightsFrom(String airportID)
    {
        String query = "SELECT * " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_DEPARTURE_LOC + " = " + airportID;

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }

    // getNumDayLandings / getNumNightLandings methods
    public int getNumDayLandings()
    {
        String query = "SELECT SUM(" + COLUMN_NR_DAY_LDG + ") " +
                       "FROM " + TABLE_FLIGHT_LOG;

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    public int getNumDayLanding(int numDays)
    {
        String query = "SELECT SUM(" + COLUMN_NR_DAY_LDG + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " ";

        if(numDays > 1)
        {
            query += "WHERE " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAYS')";
        }
        else
        {
            query += "WHERE " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAY')";
        }

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    public int getNumNightLandings()
    {
        String query = "SELECT SUM(" + COLUMN_NR_NGT_LDG + ") " +
                "FROM " + TABLE_FLIGHT_LOG;

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    public int getNumNightLanding(int numDays)
    {
        String query = "SELECT SUM(" + COLUMN_NR_NGT_LDG + ") " +
                "FROM " + TABLE_FLIGHT_LOG + " ";

        if(numDays > 1)
        {
            query += "WHERE " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAYS')";
        }
        else
        {
            query += "WHERE " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAY')";
        }

        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
    // Retrieve flights based on if hours were logged under the special conditions
    public LogbookEntry[] getSpecialConditionFlights(SpecialConditions conditions)
    {
        String requestedColumn;

        switch(conditions)
        {
            case NIGHT_FLYING:
                requestedColumn = COLUMN_NGT_TIME;
                break;
            case ACUTAL_INSTRUMENT:
                requestedColumn = COLUMN_ACTL_INST_TIME;
                break;
            case SIMULATED_INSTRUMENT:
                requestedColumn = COLUMN_SIM_INST_TIME;
                break;
            case FLIGHT_SIMULATOR:
                requestedColumn = COLUMN_FLGT_SIM_TIME;
                break;
            case CROSS_COUNTRY:
                requestedColumn = COLUMN_XCOUNTRY_TIME;
                break;
            case AS_FLIGHT_INSTRUCTOR:
                requestedColumn = COLUMN_AS_FLGT_INSTRUCT;
                break;
            case DUAL_RECIEVED:
                requestedColumn = COLUMN_DUAL_RECIVED;
                break;
            case PILOT_IN_COMMAND:
                requestedColumn = COLUMN_PIC_TIME;
                break;
            default:
                throw new UnsupportedOperationException("That condition option is not supported yet.");
        }

        String query = "SELECT * " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + requestedColumn + " > 0";

        
        // Submit Query
        // Return the result

        throw new UnsupportedOperationException();
    }
}
