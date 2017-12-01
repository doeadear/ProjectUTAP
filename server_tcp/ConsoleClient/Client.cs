using System.Net.Sockets;
using System.Net;
using System;
using SocketDemo;

namespace ConsoleClient
{
    /// <summary>
    /// Client class
    /// </summary>
    public class Client
    {
        /// <summary>
        /// Connect to server
        /// </summary>
        public void ConnectToServer()
        {
            string hostIP = "127.0.0.1";

            IPAddress ipa = IPAddress.Parse(hostIP);
            IPEndPoint ipe = new IPEndPoint(ipa, 1234);
            TcpClient tcpClient = new TcpClient();

            try
            {
                Console.WriteLine("Server IP=" + ipa.ToString());
                Console.WriteLine("Connecting...\n");

                tcpClient.Connect(ipe);

                if (tcpClient.Connected)
                {
                    Console.WriteLine("Connected!");
                    CommunicationBase cb = new CommunicationBase();
                    cb.SendMsg("Test Message", tcpClient);
                    Console.WriteLine(cb.ReceiveMsg(tcpClient));
                }
                else
                {
                    Console.WriteLine("Fail!");
                }
                Console.Read();
            }
            catch (Exception ex)
            {
                tcpClient.Close();
                Console.WriteLine(ex.Message);
                Console.Read();
            }
        }
    }
}