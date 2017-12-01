using System.Collections.Generic;
using System.Net.Sockets;
using System.Linq;
using System.Net;
using System;
using SocketDemo;

namespace ConsoleServer
{
    /// <summary>
    /// Server class
    /// </summary>
    public class Server
    {
        List<User> UserList;
        List<Lot> LotList;
        List<Schedule> ScheduleList;

        public Server()
        {
            UserList = new List<User>();
            LotList = new List<Lot>();
            ScheduleList = new List<Schedule>();
        }

        /// <summary>
        /// ListenToConnection
        /// </summary>
        public void ListenToConnection()
        {
            //Local IP
            Console.WriteLine("IP List:");

            foreach (IPAddress address in Dns.GetHostEntry("localhost").AddressList)
                Console.WriteLine(address.ToString());

            IPAddress ipAddress = Dns.GetHostEntry("localhost").AddressList[1];

            Console.WriteLine("\nLocal IP=" + ipAddress.ToString());

            IPEndPoint ipe = new IPEndPoint(ipAddress, 1234);
            TcpListener tcpListener = new TcpListener(ipe);

            tcpListener.Start();

            Console.WriteLine("\nWaiting... \n");

            TcpClient tmpTcpClient;

            while (true)
            {
                try
                {
                    tmpTcpClient = tcpListener.AcceptTcpClient();

                    if (tmpTcpClient.Connected)
                    {
                        Console.WriteLine("Connected!");

                        Communicate(tmpTcpClient);
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                    Console.Read();
                }
            } // end while
        } // end ListenToConnect()

        /// <summary>
        /// Communicate
        /// </summary>
        private void Communicate(TcpClient mTcpClient)
        {
            try
            {
                CommunicationBase cb = new CommunicationBase();

                string[] msg = cb.ReceiveMsg(mTcpClient).Split(';');
                string result = "Error";

                switch (msg[0])
                {
                    case "Register":
                        if (UserList.Count(x => x.Account == msg[1]) == 0)
                        {
                            UserList.Add(new User()
                            {
                                Account = msg[1],
                                Password = msg[2]
                            });

                            result = "true";
                        }
                        else
                        {
                            result = string.Format("false;User Account:{0} Exists!", msg[1]);
                        }
                        break;

                    case "LogIn":
                        if (UserList.Count(x => x.Account == msg[1] && x.Password == msg[2]) == 0)
                            result = "false";
                        else
                            result = "true";
                        break;

                    case "AddSchedule":
                        if (msg.Length == 5)
                        {
                            ScheduleList.Add(new Schedule()
                            {
                                Account = msg[1],
                                Lot = msg[2],
                                Distance = msg[3],
                                Time = msg[4]
                            });

                            result = "true";
                        }
                        else
                        {
                            result = "Incorrect format!";
                        }
                        break;

                    case "GetSchedule":
                        Schedule[] schedules = ScheduleList.Where(x => x.Account == msg[1]).ToArray();

                        result = "";

                        foreach (Schedule s in schedules)
                        {
                            result += string.Format("{0};{1};{2}@", s.Lot, s.Distance, s.Time);
                        }
                        break;

                    default:
                        result = "Unknown function: " + msg[0];
                        break;
                }

                cb.SendMsg(result, mTcpClient);
            }
            catch
            {
                Console.WriteLine("Client disconnected!");
                mTcpClient.Close();
                Console.Read();
            }
        } // end Communicate()
    } // end class
} // end namespace