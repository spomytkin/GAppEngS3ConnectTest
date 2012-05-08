package spomytkin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazon.s3shell.S3Store;




/**
 * Servlet implementation class BucketsListing
 */
public class BucketsListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BucketsListing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				String secretKey="-";
				String accessKey="-";
				S3Store s3 = new S3Store("s3.amazonaws.com", accessKey, secretKey);
		
				PrintWriter pw = response.getWriter();
				response.setContentType("text/plain");
				pw.println("===========================================\n");
				pw.println("Test connectivity with Amazon S3 from GAE\n");
				pw.println("===========================================\n");

				try {

					/*
					 * List the buckets in your account
					 */
					pw.println("Listing buckets");
					List<String> buckets = s3.listBuckets();
					
					for (String bucket : buckets) {
						pw.println("\n - " + bucket);
					}
					pw.println();
					
					s3.setBucket("dtccTest");

					// upload an item as public-read
			//		pw.println("upload an item as public-read");
			//		s3.storeItem("GAppEngS3ConnectTest", new String("hello from GAppEngS3ConnectTest").getBytes(), "public-read");
					pw.println("Listing items");
					List<String> items = s3.listItems();
					for (String item : items) {
						pw.println("\n - " + item);
					}
					pw.println();
				} catch (Exception ase) {
					System.out
							.println("Caught an Exception "+ase);
					pw.println("Error Message:    " + ase.getMessage());

				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
