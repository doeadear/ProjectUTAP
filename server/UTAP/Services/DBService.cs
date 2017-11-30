using System.Collections.Generic;
using MySql.Data.MySqlClient;
using UTAP.Models;

namespace UTAP.Services
{
    public class DBService
    {
        public string dbHost = "localhost";
        public string dbUser = "root";
        public string dbPass = "86750831";
        public string dbName = "utap";

        string connStr = "";

        public DBService()
        {
            connStr = "server=" + dbHost + ";uid=" + dbUser + ";pwd=" + dbPass + ";database=" + dbName;
        }

        public Dictionary<string, object> Register(UserView user)
        {
            string message = "";
            int result = 0;

            MySqlConnection conn = new MySqlConnection(connStr);

            try
            {
                conn.Open();

                MySqlCommand cmd = new MySqlCommand(
                    string.Format(
                        @"INSERT INTO `user`(`uid`, `email`, `password`, `m_AvailableColor`, `m_BusyColor`, `m_UnavailableColor`)  VALUES ({0},{1},{2},{3},{4},{5})",
                        user.uid, user.email, user.password, user.m_AvailableColor, user.m_BusyColor, user.m_UnavailableColor), conn);

                result = cmd.ExecuteNonQuery();

                conn.Close();
                conn.Dispose();
            }
            catch (MySqlException e)
            {
                message = e.ToString();
            }

            return new Dictionary<string, object>() {
                { "Success", result==1},
                { "Message", message },
                { "Data", new { } }
            };
        }

        public Dictionary<string, object> GetUser(string uid)
        {
            string message = "";
            bool success = false;
            User user = new User();

            MySqlConnection conn = new MySqlConnection(connStr);

            try
            {
                conn.Open();

                MySqlCommand cmd = new MySqlCommand(string.Format("select * from user where uid = '{0}'", uid), conn);
                MySqlDataReader dr = cmd.ExecuteReader();

                if (dr.HasRows)
                {
                    dr.Read();

                    user.uid = dr.GetString(0);
                    user.email = dr.GetString(1);
                    user.password = dr.GetString(2);
                    user.m_AvailableColor = dr.GetInt16(3);
                    user.m_BusyColor = dr.GetInt16(4);
                    user.m_UnavailableColor = dr.GetInt16(5);
                }

                dr.Close();
                dr.Dispose();

                conn.Close();
                conn.Dispose();

                success = true;
            }
            catch (MySqlException e)
            {
                message = e.ToString();
            }

            return new Dictionary<string, object>() {
                { "Success", success},
                { "Message", message },
                { "Data", new {user } }
            };
        }

        public Dictionary<string, object> GetLots(LotView _lot)
        {
            List<Lot> lots = new List<Lot>();
            string message = "";
            bool success = false;

            MySqlConnection conn = new MySqlConnection(connStr);

            try
            {
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();

                cmd.Connection = conn;
                cmd.CommandText = "select * from lots where 1 ";

                if (_lot.lot_id != 0) cmd.CommandText += string.Format("and lot_id = '{0}' ", _lot.lot_id);
                if (!string.IsNullOrEmpty(_lot.location)) cmd.CommandText += string.Format("and location = '{0}' ", _lot.location);
                if (!string.IsNullOrEmpty(_lot.status)) cmd.CommandText += string.Format("and status = '{0}' ", _lot.status);
                if (!string.IsNullOrEmpty(_lot.facultyOnly)) cmd.CommandText += string.Format("and facultyOnly = '{0}' ", _lot.facultyOnly);

                MySqlDataReader dr = cmd.ExecuteReader();

                if (dr.HasRows)
                {
                    while (dr.Read())
                    {
                        Lot lot = new Lot();

                        lot.lot_id = dr.GetInt16(0);
                        lot.location = dr.GetString(1);
                        lot.status = dr.GetString(2);
                        lot.facultyOnly = dr.GetString(3);

                        lots.Add(lot);
                    }
                }

                dr.Close();
                dr.Dispose();

                conn.Close();
                conn.Dispose();

                success = true;
            }
            catch (MySqlException e)
            {
                message = e.ToString();
            }

            return new Dictionary<string, object>() {
                { "Success", success},
                { "Message", message },
                { "Data", new  {lots=lots.ToArray() } }
            };
        }

        public Dictionary<string, object> GetSchedules(string uid, string lot_id)
        {
            string message = "";
            bool success = false;
            Schedule schedule = new Schedule();

            MySqlConnection conn = new MySqlConnection(connStr);

            try
            {
                conn.Open();

                MySqlCommand cmd = new MySqlCommand(string.Format("select * from schedules where uid = '{0}' and lot_id = '{1}'", uid, lot_id), conn);
                MySqlDataReader dr = cmd.ExecuteReader();

                if (dr.HasRows)
                {
                    dr.Read();

                    schedule.uid = dr.GetString(0);
                    schedule.lot_id = dr.GetString(1);
                    schedule.m_MaxLotDistance = dr.GetDouble(2);
                    schedule.m_MaxWaitTime = dr.GetDecimal(3);
                }

                dr.Close();
                dr.Dispose();

                conn.Close();
                conn.Dispose();

                success = true;
            }
            catch (MySqlException e)
            {
                message = e.ToString();
            }

            return new Dictionary<string, object>() {
                { "Success", success},
                { "Message", message },
                { "Data", new {schedule } }
            };
        }

        public Dictionary<string, object> AddSchedules(ScheduleView schedule)
        {
            string message = "";
            int result = 0;

            MySqlConnection conn = new MySqlConnection(connStr);

            try
            {
                conn.Open();

                MySqlCommand cmd = new MySqlCommand(
                    string.Format(
                        @"INSERT INTO `schedules`(`uid`, `lot_id`, `m_MaxLotDistance`, `m_MaxWaitTime`) VALUES ({0},{1},{2},{3})",
                        schedule.uid, schedule.lot_id, schedule.m_MaxLotDistance, schedule.m_MaxWaitTime), conn);

                result = cmd.ExecuteNonQuery();

                conn.Close();
                conn.Dispose();
            }
            catch (MySqlException e)
            {
                message = e.ToString();
            }

            return new Dictionary<string, object>() {
                { "Success", result==1},
                { "Message", message },
                { "Data", new { } }
            };
        }
    }
}