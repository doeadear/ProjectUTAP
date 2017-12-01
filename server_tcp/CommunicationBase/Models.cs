namespace SocketDemo
{
    public class User
    {
        public string Account { get; set; }
        public string Password { get; set; }


        public string email { get; set; }

        public int m_AvailableColor { get; set; }
        public int m_BusyColor { get; set; }
        public int m_UnavailableColor { get; set; }
    }

    public class Lot
    {
        public int lot_id { get; set; }
        public string location { get; set; }
        public string status { get; set; }
        public string facultyOnly { get; set; }
    }

    public class Schedule
    {
        public string Account { get; set; }
        public string Lot { get; set; }
        public double MaxLotDistance { get; set; }
        public decimal MaxWaitTime { get; set; }
    }
}