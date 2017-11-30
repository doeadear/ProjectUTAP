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

    public class Schedule
    {
        public string uid { get; set; }
        public string lot_id { get; set; }
        public double m_MaxLotDistance { get; set; }
        public decimal m_MaxWaitTime { get; set; }
    }
}
