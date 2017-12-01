using System.Net.Sockets;
using System.Threading;
using System.Net;
using System;

namespace ConsoleServer
{
    /// <summary>
    /// Server class
    /// </summary>
    public class Server
    {
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

            int numberOfClients = 0;

            while (true)
            {
                try
                {
                    tmpTcpClient = tcpListener.AcceptTcpClient();

                    if (tmpTcpClient.Connected)
                    {
                        Console.WriteLine("Connected!");
                        HandleClient handleClient = new HandleClient(tmpTcpClient);
                        Thread myThread = new Thread(new ThreadStart(handleClient.Communicate));

                        numberOfClients += 1;
                        myThread.IsBackground = true;
                        myThread.Start();
                        myThread.Name = tmpTcpClient.Client.RemoteEndPoint.ToString();
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                    Console.Read();
                }
            } // end while
        } // end ListenToConnect()
    } // end class
} // end namespace