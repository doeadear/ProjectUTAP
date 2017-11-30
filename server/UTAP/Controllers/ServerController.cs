using System.Collections.Generic;
using System.Web.Http;
using UTAP.Services;
using UTAP.Models;

namespace UTAP.Controllers
{
    public class ServerController : ApiController
    {
        DBService DBMgr;

        public ServerController()
        {
            DBMgr = new DBService();
        }

        [HttpPost]
        public Dictionary<string, object> Register(UserView user)
        {
            return DBMgr.Register(user);
        }

        [HttpGet]
        public Dictionary<string, object> GetUser(string uid)
        {
            return DBMgr.GetUser(uid);
        }

        [HttpPost]
        public Dictionary<string, object> GetLots(LotView lot)
        {
            return DBMgr.GetLots(lot);
        }

        [HttpGet]
        public Dictionary<string, object> GetSchedules(string uid, string lot_id)
        {
            return DBMgr.GetSchedules(uid, lot_id);
        }

        [HttpPost]
        public Dictionary<string, object> AddSchedules(ScheduleView schedule)
        {
            return DBMgr.AddSchedules(schedule);
        }
    }
}
