using System.Collections.Generic;
using System.Web.Http;
using UTAP.Services;

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
    }
}
