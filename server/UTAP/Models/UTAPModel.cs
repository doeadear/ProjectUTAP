namespace UTAP.Models
{
    public class User
    {
        public string uid { get; set; }
        public string email { get; set; }
        public string password { get; set; }
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

    public class LotView
    {
        public int lot_id { get; set; }
        public string location { get; set; }
        public string status { get; set; }
        public string facultyOnly { get; set; }
    }

    public class Schedule
    {
        public string uid { get; set; }
        public string lot_id { get; set; }
        public double m_MaxLotDistance { get; set; }
        public decimal m_MaxWaitTime { get; set; }
    }

    public class ScheduleView
    {
        public string uid { get; set; }
        public string lot_id { get; set; }
        public double m_MaxLotDistance { get; set; }
        public decimal m_MaxWaitTime { get; set; }
    }
}
