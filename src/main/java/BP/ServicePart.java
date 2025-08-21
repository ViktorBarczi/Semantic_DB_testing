package BP;

import java.util.ArrayList;
import java.util.List;
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
  public List<Output> queryBlazegraph(String endpointUrl, String queryString) {
    System.out.println("Querying Blazegraph...");
    long startTime = 0;
    long endTime = 0;
    List<Output> outputList = new ArrayList<>();
    try {
      QueryExecution qexec = QueryExecutionFactory.sparqlService(endpointUrl, queryString);

      startTime = System.nanoTime();
      ResultSet results = qexec.execSelect();
      endTime = System.nanoTime();

      while (results.hasNext()) {
        QuerySolution solution = results.next();
        Output output =
            new Output(solution.get("subject") != null ? solution.get("subject").toString() : "",
                solution.get("predicate") != null ? solution.get("predicate").toString() : "",
                solution.get("object") != null ? solution.get("object").toString() : "",
                "Blazegraph", Long.toString((endTime - startTime) / 1_000_000));

        outputList.add(output);

        System.out.println("subject: " + output.getSubject() + ",\n" + "predicate: "
            + output.getPredicate() + ",\n" + "object: " + output.getObject() + ",\n"
            + "elapsedTime (Blazegraph): " + output.getElapsedTime());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("-----------------------------------------------------------");
    return outputList;
  }

  // 2. Query GraphDB using SPARQL endpoint (also Jena)
  public List<Output> queryGraphDB(String endpointUrl, String queryString) {
    System.out.println("Querying GraphDB...");
    long startTime = 0;
    long endTime = 0;
    List<Output> outputList = new ArrayList<>();
    try {
      QueryExecution qexec = QueryExecutionFactory.sparqlService(endpointUrl, queryString);

      startTime = System.nanoTime();
      ResultSet results = qexec.execSelect();
      endTime = System.nanoTime();

      while (results.hasNext()) {
        QuerySolution solution = results.next();
        Output output =
            new Output(solution.get("subject") != null ? solution.get("subject").toString() : "",
                solution.get("predicate") != null ? solution.get("predicate").toString() : "",
                solution.get("object") != null ? solution.get("object").toString() : "", "GraphDB",
                Long.toString((endTime - startTime) / 1_000_000));

        outputList.add(output);

        System.out.println("subject: " + output.getSubject() + ",\n" + "predicate: "
            + output.getPredicate() + ",\n" + "object: " + output.getObject() + ",\n"
            + "elapsedTime (GraphDB): " + output.getElapsedTime());

      }

    } catch (Exception e) {

      e.printStackTrace();
    }
    System.out.println("-----------------------------------------------------------");
    return outputList;
  }

  // 3. Query Stardog using Stardog Java Client
  public List<Output> queryStardog(String endpointUrl, String queryString) {
    System.out.println("Querying Stardog...");

    long startTime = 0;
    long endTime = 0;
    List<Output> outputList = new ArrayList<>();

    Connection conn =
        ConnectionConfiguration.to(endpointUrl).server("https://sd-d3307c49.stardog.cloud:5820")
            .credentials("u1", "kdlrKKlsd123").connect();

    startTime = System.nanoTime();
    SelectQueryResult results = conn.select(queryString).execute();
    endTime = System.nanoTime();

    while (results.hasNext()) {
      BindingSet binding = results.next();
      Output output = new Output(
          binding.value("subject") != null ? binding.value("subject").get().toString() : "",
          binding.value("predicate") != null ? binding.value("predicate").get().toString() : "",
          binding.value("object") != null ? binding.value("object").get().toString() : "",
          "Stardog", Long.toString((endTime - startTime) / 1_000_000));

      outputList.add(output);

      System.out.println("subject: " + output.getSubject() + ",\n" + "predicate: "
          + output.getPredicate() + ",\n" + "object: " + output.getObject() + ",\n"
          + "elapsedTime (Stardog): " + output.getElapsedTime());
    }

    System.out.println("-----------------------------------------------------------");
    return outputList;
  }
}
