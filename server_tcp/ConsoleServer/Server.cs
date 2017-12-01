using System.Collections.Generic;
using System.Net.Sockets;
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
                string msg = cb.ReceiveMsg(mTcpClient);
                Console.WriteLine(msg + "\n");

                cb.SendMsg("true", mTcpClient);
            }
            catch
            {
                Console.WriteLine("Client disconnected!");
                mTcpClient.Close();
                Console.Read();
            }
        } // end HandleClient()
    } // end class
} // end namespace