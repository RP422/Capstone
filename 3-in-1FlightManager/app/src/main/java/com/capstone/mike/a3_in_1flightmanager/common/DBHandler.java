package com.capstone.mike.a3_in_1flightmanager.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.capstone.mike.a3_in_1flightmanager.logbook.AircraftCategory;
import com.capstone.mike.a3_in_1flightmanager.logbook.AircraftClass;
import com.capstone.mike.a3_in_1flightmanager.logbook.LogbookEntry;
import com.capstone.mike.a3_in_1flightmanager.logbook.SpecialConditions;

import org.json.JSONObject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Mike on 10/11/2017.
 */

public class DBHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "logbookInfo";

    private static final String TABLE_FLIGHT_LOG = "flightLog";
    private static final String COLUMN_LOG_ID = "ID";
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

    private static final String TABLE_JSON_STORE = "jsonStore";
    private static final String COLUMN_REFERENCE_NAME = "REFERENCE_NAME";
    private static final String COLUMN_SCHEMA = "SCHEMA";
    private static final String COLUMN_JSON = "JSON";

    // TODO Create another table that maps schemas to int

    private static DBHandler dbInstance;
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    // Private to prevent instances from being created willy-nilly
    private DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // The replacement for the constructor to ensure that there's only ever
    //   one instance of DBHandler alive at one time.
    public static synchronized DBHandler getInstance(Context context)
    {
        if (dbInstance == null)
        {
            dbInstance = new DBHandler(context.getApplicationContext());
        }
        return dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // First creation of the Table
        String createFlightLog = // Prepare yourself, this is gonna be beautiful
                "CREATE TABLE "             +     TABLE_FLIGHT_LOG        + "(" +
                COLUMN_LOG_ID               +  " INTEGER PRIMARY KEY, "   +
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
                COLUMN_PIC_TIME             +         " FLOAT)"           ;

        String createJSONStore =
                "CREATE TABLE " + TABLE_JSON_STORE + "(" +
                COLUMN_REFERENCE_NAME +     " VARCHAR(100) PRIMARY KEY, " +
                COLUMN_SCHEMA         +           " VARCHAR(25), "        +
                COLUMN_JSON           +         " VARCHAR(100000)) "       ; // This might be excessive, but I'm not taking the chance

        db.execSQL(createFlightLog);
        db.execSQL(createJSONStore);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        // Update table from older version
            // Unnecessary step on DB Ver 1, as this is the only version right now.
    }
//    @Override
//    public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion)
//    {
//        // Downgrade table from newer version
//            // Should only be necessary if I need to roll back changes made in an update.
//    }

    // // General methods
    private Cursor submitQuery(String query)
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(query, null);
    }
    public void createNewEntry(ContentValues values, String table)
    {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try
        {
            db.insertOrThrow(table, null, values);
            db.setTransactionSuccessful();
        }
        catch(Exception e)
        {
            Log.d("Creating Entry", "There was an error inserting the database entry");
        }
        finally
        {
            db.endTransaction();
        }
    }
    public void updateEntry(ContentValues values, String table, String whereClause)
    {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try
        {
            int rows = db.update(table, values, whereClause, null);
            Cursor cursor;

            if(rows == 1)
            {
                switch(table)
                {
                    case TABLE_FLIGHT_LOG:
                        cursor = submitQuery("SELECT * " +
                                "FROM " + TABLE_FLIGHT_LOG + " " +
                                "WHERE " + COLUMN_LOG_ID + " = " + values.getAsInteger(COLUMN_LOG_ID));
                        break;
                    case TABLE_JSON_STORE:
                        cursor = submitQuery("SELECT * " +
                                "FROM " + TABLE_JSON_STORE + " " +
                                "WHERE " + COLUMN_REFERENCE_NAME + " = " + values.getAsString(COLUMN_REFERENCE_NAME));
                        break;
                    default:
                        throw new UnsupportedOperationException();
                }

                try
                {
                    if(cursor.moveToFirst())
                    {
                        String id = cursor.getString(0);
                        db.setTransactionSuccessful();
                    }
                }
                finally
                {
                    if(cursor != null && !cursor.isClosed())
                    {
                        cursor.close();
                    }
                }
            }

            // TODO Think about including a "entry does not exist" failsafe?
        }
        catch(Exception e)
        {
            Log.d("Updating Entry", "There was an error updating the database");
        }
        finally
        {
            db.endTransaction();
        }
    }
    public void deleteEntry(String table, String whereClause)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try
        {
            db.delete(table, whereClause, null);
            db.setTransactionSuccessful();
        }
        catch(Exception e)
        {
            Log.d("Delete Entry", "Error while trying to delete from the database");
        }
        finally
        {
            db.endTransaction();
        }
    }

    // // Logbook methods
    // General
    private static ContentValues translateToContentValues(LogbookEntry entry, boolean includeID)
    {
        ContentValues values = new ContentValues();

        if(includeID)
        {
            values.put(COLUMN_LOG_ID, entry.id);
        }

        values.put(COLUMN_DATE,                 dateFormat.format(entry.date));
        values.put(COLUMN_AIRCRAFT_MODEL,       entry.aircraftModel);
        values.put(COLUMN_AIRCRAFT_IDENT,       entry.aircraftID);
        values.put(COLUMN_DEPARTURE_LOC,        entry.flightDeparture);
        values.put(COLUMN_ARRIVAL_LOC,          entry.flightArrival);
        values.put(COLUMN_NR_INST_APP,          entry.numInsturmentApproach);
        values.put(COLUMN_RMKS_AND_ENDSMTS,     entry.remarksAndEndorsements);
        values.put(COLUMN_NR_DAY_LDG,           entry.numDayLandings);
        values.put(COLUMN_NR_NGT_LDG,           entry.numNightLandings);
        values.put(COLUMN_AIRCRAFT_CATEGORY,    entry.aircraftCategory.toString());
        values.put(COLUMN_CATEGORY_FLIGHT_TIME, entry.flightTime);
        values.put(COLUMN_AIRCRAFT_CLASS,       entry.aircraftClass.toString());
        values.put(COLUMN_CLASS_FLIGHT_TIME,    entry.classFlightTime);
        values.put(COLUMN_NGT_TIME,             entry.nightFlyingTime);
        values.put(COLUMN_ACTL_INST_TIME,       entry.actualInstrumentTime);
        values.put(COLUMN_SIM_INST_TIME,        entry.simulatedInstrumentTime);
        values.put(COLUMN_FLGT_SIM_TIME,        entry.flightSimulatorTime);
        values.put(COLUMN_XCOUNTRY_TIME,        entry.crossCountryTime);
        values.put(COLUMN_AS_FLGT_INSTRUCT,     entry.asFlightInstructorTime);
        values.put(COLUMN_DUAL_RECIVED,         entry.dualRecievedTime);
        values.put(COLUMN_PIC_TIME,             entry.pilotInCommandTime);

        return values;
    }
    private ArrayList<LogbookEntry> getEntries(String query)
    {
        ArrayList<LogbookEntry> entries = new ArrayList<LogbookEntry>();
        Cursor cursor = submitQuery(query);

        try
        {
            if(cursor.moveToFirst())
            {
                do
                {
                    LogbookEntry entry = new LogbookEntry();

                    // Again, start with the simple ones
                    entry.id =                      cursor.getInt   (cursor.getColumnIndex(COLUMN_LOG_ID));
                    entry.aircraftModel =           cursor.getString(cursor.getColumnIndex(COLUMN_AIRCRAFT_MODEL));
                    entry.aircraftID =              cursor.getString(cursor.getColumnIndex(COLUMN_AIRCRAFT_IDENT));
                    entry.flightDeparture =         cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTURE_LOC));
                    entry.flightArrival =           cursor.getString(cursor.getColumnIndex(COLUMN_ARRIVAL_LOC));
                    entry.numInsturmentApproach =   cursor.getInt   (cursor.getColumnIndex(COLUMN_NR_INST_APP));
                    entry.remarksAndEndorsements =  cursor.getString(cursor.getColumnIndex(COLUMN_RMKS_AND_ENDSMTS));
                    entry.numDayLandings =          cursor.getInt   (cursor.getColumnIndex(COLUMN_NR_DAY_LDG));
                    entry.numNightLandings =        cursor.getInt   (cursor.getColumnIndex(COLUMN_NR_NGT_LDG));
                    entry.flightTime =              cursor.getFloat (cursor.getColumnIndex(COLUMN_CATEGORY_FLIGHT_TIME));
                    entry.classFlightTime =         cursor.getFloat (cursor.getColumnIndex(COLUMN_CLASS_FLIGHT_TIME));
                    entry.nightFlyingTime =         cursor.getFloat (cursor.getColumnIndex(COLUMN_NGT_TIME));
                    entry.actualInstrumentTime =    cursor.getFloat (cursor.getColumnIndex(COLUMN_ACTL_INST_TIME));
                    entry.simulatedInstrumentTime = cursor.getFloat (cursor.getColumnIndex(COLUMN_SIM_INST_TIME));
                    entry.flightSimulatorTime =     cursor.getFloat (cursor.getColumnIndex(COLUMN_FLGT_SIM_TIME));
                    entry.crossCountryTime =        cursor.getFloat (cursor.getColumnIndex(COLUMN_XCOUNTRY_TIME));
                    entry.asFlightInstructorTime =  cursor.getFloat (cursor.getColumnIndex(COLUMN_AS_FLGT_INSTRUCT));
                    entry.dualRecievedTime =        cursor.getFloat (cursor.getColumnIndex(COLUMN_DUAL_RECIVED));
                    entry.pilotInCommandTime =      cursor.getFloat (cursor.getColumnIndex(COLUMN_PIC_TIME));

                    // And then move onto the harder ones
                    try
                    {
                        java.util.Date date = dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                        entry.date = new Date(date.getTime());
                    }
                    catch (ParseException e)
                    {
                        Log.d("Parse", "There was an error parsing the date");
                    }

                    switch(cursor.getString(cursor.getColumnIndex(COLUMN_AIRCRAFT_CATEGORY)))
                    {
                        case "SINGLE_ENGINE_LAND":
                            entry.aircraftCategory = AircraftCategory.SINGLE_ENGINE_LAND;
                            break;
                        case "MULTI_ENGINE_LAND":
                            entry.aircraftCategory = AircraftCategory.MULTI_ENGINE_LAND;
                            break;
                    }

                    switch(cursor.getString(cursor.getColumnIndex(COLUMN_AIRCRAFT_CLASS)))
                    {
                        case "TAIL_WHEEL":
                            entry.aircraftClass = AircraftClass.TAIL_WHEEL;
                            break;
                        case "GLIDER":
                            entry.aircraftClass = AircraftClass.GLIDER;
                            break;
                    }

                    entries.add(entry);
                }
                while(cursor.moveToNext());
            }
        }
        catch(Exception e)
        {
            Log.d("Query", "There was a problem retrieving logbook entries from the database");
        }
        finally
        {
            if(cursor != null && !cursor.isClosed())
            {
                cursor.close();
            }
        }

        return entries;
    }

    // Create
    public void createLogbookEntry(LogbookEntry entry)
    {
        ContentValues values = translateToContentValues(entry, false);

        createNewEntry(values, TABLE_FLIGHT_LOG);
    }

    // Read
    private float getAggregateFloat(String query)
    {
        Cursor cursor = submitQuery(query);

        if(cursor.moveToFirst())
        {
            return cursor.getFloat(0);
        }
        else
        {
            return 0;
        }
    }
    private int getAggregateInt(String query)
    {
        Cursor cursor = submitQuery(query);

        if(cursor.moveToFirst())
        {
            return cursor.getInt(0);
        }
        else
        {
            return 0;
        }
    }

    public void addNewEntry(LogbookEntry entry)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = translateToContentValues(entry, false);

        // TODO Submit entry into the database
    }

    public LogbookEntry[] getAll()
    {
        String query = "SELECT * " +
                       "FROM " + TABLE_FLIGHT_LOG;

        return (LogbookEntry[])getEntries(query).toArray();
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

        return (LogbookEntry[])getEntries(query).toArray();
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

        return getAggregateFloat(query);
    }

    public float getTotalHoursFlown(AircraftCategory category)
    {
        String query = "SELECT SUM(" + COLUMN_CLASS_FLIGHT_TIME + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_AIRCRAFT_CATEGORY + " = " + category;

        return getAggregateFloat(query);
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

        return getAggregateFloat(query);
    }

    public float getTotalHoursFlown(AircraftClass aircraftClass)
    {
        String query = "SELECT SUM(" + COLUMN_CLASS_FLIGHT_TIME + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_AIRCRAFT_CATEGORY + " = " + aircraftClass;

        return getAggregateFloat(query);
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

        return getAggregateFloat(query);
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

        return getAggregateFloat(query);
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

        return getAggregateFloat(query);
    }

    public float getTotalHoursFlown(String id)
    {
        String query = "SELECT SUM(" + COLUMN_CLASS_FLIGHT_TIME + ") " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_AIRCRAFT_IDENT + " = " + id;

        return getAggregateFloat(query);
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

        return getAggregateFloat(query);
    }
    // TODO getHoursFlown like the above methods, but with a date range instead of the last X number of days?

    public LogbookEntry[] getEntries(Date date)
    {
        String query = "SELECT * " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE STRFTIME('%y-%m-%d'" + COLUMN_DATE + ") = " + dateFormat.format(date);

        return (LogbookEntry[])getEntries(query).toArray();
    }

    public LogbookEntry[] getFlightsTo(String airportID)
    {
        String query = "SELECT * " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_ARRIVAL_LOC + " = " + airportID;

        return (LogbookEntry[])getEntries(query).toArray();
    }
    public LogbookEntry[] getFlightsFrom(String airportID)
    {
        String query = "SELECT * " +
                       "FROM " + TABLE_FLIGHT_LOG + " " +
                       "WHERE " + COLUMN_DEPARTURE_LOC + " = " + airportID;

        return (LogbookEntry[])getEntries(query).toArray();
    }

    public int getNumDayLandings()
    {
        String query = "SELECT SUM(" + COLUMN_NR_DAY_LDG + ") " +
                       "FROM " + TABLE_FLIGHT_LOG;

        return getAggregateInt(query);
    }
    public int getNumDayLandings(int numDays)
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

        return getAggregateInt(query);
    }
    public int getNumNightLandings()
    {
        String query = "SELECT SUM(" + COLUMN_NR_NGT_LDG + ") " +
                "FROM " + TABLE_FLIGHT_LOG;

        return getAggregateInt(query);
    }
    public int getNumNightLandings(int numDays)
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

        return getAggregateInt(query);
    }
    public int getTotalLandings()
    {
        String query = "SELECT SUM(" + COLUMN_NR_NGT_LDG + ") " +
                "FROM " + TABLE_FLIGHT_LOG;

        return getAggregateInt(query);
    }
    public int getTotalLandings(int numDays)
    {
        String query = "SELECT SUM(" + COLUMN_NR_DAY_LDG + " + " + COLUMN_NR_NGT_LDG + ") " +
                "FROM " + TABLE_FLIGHT_LOG + " ";

        if(numDays > 1)
        {
            query += "WHERE " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAYS')";
        }
        else
        {
            query += "WHERE " + COLUMN_DATE + " >= DATE('now, 'start of day', -" + numDays + "DAY')";
        }

        return getAggregateInt(query);
    }

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

        return (LogbookEntry[])getEntries(query).toArray();
    }
    // TODO getSpecialConditionsFlights with a dateRange
    // TODO getSpecialConditionsHours, both variants

    // Update
    public void updateLogbook(LogbookEntry entry)
    {
        ContentValues values = translateToContentValues(entry, true);

        updateEntry(values, TABLE_FLIGHT_LOG, COLUMN_LOG_ID + " = " + entry.id);
    }

    // Delete
    public void removeLogbookEntry(int id)
    {
        deleteEntry(TABLE_FLIGHT_LOG, COLUMN_LOG_ID + " = " + id);
    }
    public void removeAllLogbookEntries()
    {
        deleteEntry(TABLE_FLIGHT_LOG, null);
    }

    // // JSON Store methods
    // General
    public ContentValues translateToContentValues(String referenceName, JSONSchema schema, JSONObject json)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_REFERENCE_NAME, referenceName);
        values.put(COLUMN_SCHEMA, schema.toString());
        values.put(COLUMN_JSON, json.toString());

        return values;
    }

    // Create
    public void insertJSON(String referenceName, JSONSchema schema, JSONObject json)
    {
        ContentValues values = translateToContentValues(referenceName, schema, json);

        createNewEntry(values, TABLE_JSON_STORE);
    }

    // Read
    public String[] getFilesOfSchema(JSONSchema schema)
    {
        // TODO When I get that schema mapping table up, do the translation here
        String query = "SELECT " + COLUMN_REFERENCE_NAME + " " +
                "FROM " + TABLE_JSON_STORE + " " +
                "WHERE " + COLUMN_SCHEMA + " = " + schema;

        ArrayList<String> referenceNames = new ArrayList<String>();
        Cursor cursor = submitQuery(query);

        try
        {
            if(cursor.moveToFirst())
            {
                do
                {
                    referenceNames.add(cursor.getString(cursor.getColumnIndex(COLUMN_REFERENCE_NAME)));
                }
                while(cursor.moveToNext());
            }
        }
        catch(Exception e)
        {
            Log.d("Query", "There was a problem retrieving JSON files from the database");
        }
        finally
        {
            if(cursor != null && !cursor.isClosed())
            {
                cursor.close();
            }
        }

        return (String[])referenceNames.toArray();
    }
    public JSONObject getJSONfromReferenceName(String referenceName)
    {
        String query = "SELECT " + COLUMN_JSON + " " +
                       "FROM " + TABLE_JSON_STORE + " " +
                       "WHERE " + COLUMN_REFERENCE_NAME + " = " + referenceName;

        Cursor cursor = submitQuery(query);

        JSONObject json = null;

        try
        {
            if(cursor.moveToFirst())
            {
                json = new JSONObject(cursor.getString(cursor.getColumnIndex(COLUMN_JSON)));
            }
        }
        catch(Exception e)
        {
            Log.d("Query", "There was a problem retrieving the requested JSON file");
        }
        finally
        {
            if(cursor != null && !cursor.isClosed())
            {
                cursor.close();
            }
        }

        return json;
    }

    // Update
    public void updateJSON(String referenceName, JSONSchema schema, JSONObject json)
    {
        ContentValues values = translateToContentValues(referenceName, schema, json);

        updateEntry(values, TABLE_JSON_STORE, COLUMN_REFERENCE_NAME + " = " + values.getAsString(COLUMN_REFERENCE_NAME));
    }

    // Delete
    public void deleteJSON(String referenceName)
    {
        deleteEntry(TABLE_JSON_STORE, COLUMN_REFERENCE_NAME + " = " + referenceName);
    }
}
