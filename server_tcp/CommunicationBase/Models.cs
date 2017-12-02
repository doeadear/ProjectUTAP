namespace SocketDemo
{
    public class User
    {
        public string Account { get; set; }
        public string Password { get; set; }
        public string ColorFull { get; set; }
        public string ColorBusy { get; set; }
        public string ColorFree { get; set; }
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
        public string Distance { get; set; }
        public string Time { get; set; }
    }
}