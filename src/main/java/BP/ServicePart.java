package BP;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.stardog.stark.query.BindingSet;
import com.stardog.stark.query.SelectQueryResult;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Service;

@Service
public class ServicePart {
  // 1. Query Blazegraph or any SPARQL endpoint (using Apache Jena)
  public String queryBlazegraph(String endpointUrl, String queryString) {
    System.out.println("Querying Blazegraph...");
    long startTime = 0;
    long endTime = 0;
    StringBuilder output = new StringBuilder();
    try {
      QueryExecution qexec = QueryExecutionFactory.sparqlService(endpointUrl, queryString);

      startTime = System.nanoTime();
      ResultSet results = qexec.execSelect();
      endTime = System.nanoTime();

      while (results.hasNext()) {
        QuerySolution solution = results.next();
        output.append("subject: ")
            .append(solution.get("subject") != null ? solution.get("subject").toString() : "")
            .append(",\n").append("predicate: ")
            .append(solution.get("predicate") != null ? solution.get("predicate").toString() : "")
            .append(",\n").append("object: ")
            .append(solution.get("object") != null ? solution.get("object").toString() : "")
            .append(",\n");

      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    long durationMs = (endTime - startTime) / 1_000_000;
    System.out.println(output.toString() + "Elapsed time in miliseconds (Balzegraph): "
        + Long.toString(durationMs));
    System.out.println("-----------------------------------------------------------");
    return output.toString() + "\n" + "Elapsed time in miliseconds (Balzegraph): "
        + Long.toString(durationMs) + "\n";
  }

  // 2. Query GraphDB using SPARQL endpoint (also Jena)
  public String queryGraphDB(String endpointUrl, String queryString) {
    System.out.println("Querying GraphDB...");
    long startTime = 0;
    long endTime = 0;
    StringBuilder output = new StringBuilder();
    try {
      QueryExecution qexec = QueryExecutionFactory.sparqlService(endpointUrl, queryString);

      startTime = System.nanoTime();
      ResultSet results = qexec.execSelect();
      endTime = System.nanoTime();

      while (results.hasNext()) {
        QuerySolution solution = results.next();
        output.append("subject: ")
            .append(solution.get("subject") != null ? solution.get("subject").toString() : "")
            .append(",\n").append("predicate: ")
            .append(solution.get("predicate") != null ? solution.get("predicate").toString() : "")
            .append(",\n").append("object: ")
            .append(solution.get("object") != null ? solution.get("object").toString() : "")
            .append(",\n");

      }

    } catch (Exception e) {

      e.printStackTrace();
    }
    long durationMs = (endTime - startTime) / 1_000_000;
    System.out.println(
        output.toString() + "Elapsed time in miliseconds (GraphDB): " + Long.toString(durationMs));
    System.out.println("-----------------------------------------------------------");
    return output.toString() + "\n" + "Elapsed time in miliseconds (GraphDB): "
        + Long.toString(durationMs) + "\n";
  }

  // 3. Query Stardog using Stardog Java Client
  public String queryStardog(String endpointUrl, String queryString) {
    System.out.println("Querying Stardog...");

    long startTime = 0;
    long endTime = 0;
    StringBuilder output = new StringBuilder();

    Connection conn =
        ConnectionConfiguration.to(endpointUrl).server("https://sd-d3307c49.stardog.cloud:5820")
            .credentials("u1", "kdlrKKlsd123").connect();

    startTime = System.nanoTime();
    SelectQueryResult results = conn.select(queryString).execute();
    endTime = System.nanoTime();

    while (results.hasNext()) {
      BindingSet binding = results.next();
      output.append("subject: ")
          .append(binding.value("subject") != null ? binding.value("subject").get().toString() : "")
          .append(",\n").append("predicate: ")
          .append(
              binding.value("predicate") != null ? binding.value("predicate").get().toString() : "")
          .append(",\n").append("object: ")
          .append(binding.value("object") != null ? binding.value("object").get().toString() : "")
          .append(",\n");
    }

    long durationMs = (endTime - startTime) / 1_000_000;
    System.out.println(output + "Elapsed time in milliseconds (Stardog): " + durationMs);
    System.out.println("-----------------------------------------------------------");
    return output + "Elapsed time in milliseconds (Stardog): " + durationMs + "\n";
  }
}
