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

        [HttpGet]
        public Dictionary<string, object> GetUser(string uid)
        {
            return DBMgr.GetUser(uid);
        }

        [HttpPost]
        public Dictionary<string, object> GetLots(int lot_id = 0, string location = "", string status = "", string facultyOnly = "")
        {
            return DBMgr.GetLots(lot_id, location, status, facultyOnly);
        }

        [HttpGet]
        public Dictionary<string, object> GetSchedules(string uid, string lot_id)
        {
            return DBMgr.GetSchedules(uid, lot_id);
        }
    }
}
