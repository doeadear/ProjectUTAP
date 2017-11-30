﻿using System.Collections.Generic;
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

                    dr.Close();
                    dr.Dispose();
                }

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

                    dr.Close();
                    dr.Dispose();
                }

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
    }
}