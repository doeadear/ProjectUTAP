using MySql.Data.MySqlClient;

namespace UTAP.Services
{
    public class DBService
    {
        public string dbHost = "localhost";
        public string dbUser = "root";
        public string dbPass = "86750831";
        public string dbName = "utap";

        public DBService()
        {
            string connStr = "server=" + dbHost + ";uid=" + dbUser + ";pwd=" + dbPass + ";database=" + dbName;

            MySqlConnection conn = new MySqlConnection(connStr);

            try
            {
                conn.Open();
            }
            catch (MySqlException ex)
            {
            }
        }
    }
}