package BP;

public class Utils {
  private static String blazegraphUrl64MB =
      "http://172.25.176.1:9999/blazegraph/namespace/kb/sparql";
  private static String graphdbUrl64MB = "http://IBM-PF3DYWLV:7200/repositories/BP";
  private static String stardogUrl64MB = "BP";

  private static String blazegraphUrl321MB =
      "http://172.25.176.1:9999/blazegraph/namespace/BIG/sparql";
  private static String graphdbUrl321MB = "http://IBM-PF3DYWLV:7200/repositories/GB";
  private static String stardogUrl321MB = "BIG";

  private static String blazegraphUrl1GB =
      "http://172.25.176.1:9999/blazegraph/namespace/oneGB/sparql";
  private static String graphdbUrl1GB = "http://IBM-PF3DYWLV:7200/repositories/BigDB";
  private static String stardogUrl1GB = "GB";

  public static String getBlazegraphUrl64MB() {
    return blazegraphUrl64MB;
  }

  public static String getGraphdbUrl64MB() {
    return graphdbUrl64MB;
  }

  public static String getStardogUrl64MB() {
    return stardogUrl64MB;
  }

  public static String getBlazegraphUrl321MB() {
    return blazegraphUrl321MB;
  }

  public static String getBlazegraphUrl1GB() {
    return blazegraphUrl1GB;
  }

  public static String getGraphdbUrl321MB() {
    return graphdbUrl321MB;
  }

  public static String getGraphdbUrl1GB() {
    return graphdbUrl1GB;
  }

  public static String getStardogUrl1GB() {
    return stardogUrl1GB;
  }

  public static String getStardogUrl321MB() {
    return stardogUrl321MB;
  }


  public static String getBasicQuery(String object) {
    return "SELECT ?subject ?predicate ?object\r\n" + "WHERE{\r\n"
        + "?subject ?predicate ?object .\r\n" + "FILTER(STR(?object) = \"" + object + "\")\r\n"
        + "}";
  }

  public static String getBlazegraphBDSQuery(String object) {
    return "PREFIX bds: <http://www.bigdata.com/rdf/search#>\r\n"
        + "SELECT ?subject ?predicate ?object\r\n" + "WHERE {\r\n" + "?object bds:search \""
        + object + "\" .\r\n" + "?object bds:matchAllTerms \"true\" .\r\n"
        + "?object bds:minRelevance \"0.99\" .\r\n" + "?subject ?predicate ?object .\r\n" + "}";
  }

  public static String getGraphDBLUCQuery(String object) {
    return "PREFIX luc: <http://www.ontotext.com/connectors/lucene#>\r\n"
        + "PREFIX inst: <http://www.ontotext.com/connectors/lucene/instance#>\r\n"
        + "SELECT ?subject ?predicate ?object {\r\n" + "  ?search a inst:FTS ;\r\n"
        + "          luc:query \"\\\"" + object + "\\\"\" ;\r\n"
        + "          luc:entities ?subject .\r\n" + "\r\n" + "  ?subject ?predicate ?object .\r\n"
        + "  FILTER(?object = \"" + object + "\")\r\n" + "}\r\n" + "";
  }

  public static String getStardogFTSQuery(String object) {
    return "PREFIX fts: <tag:stardog:api:search:>\r\n"
        + "SELECT ?subject ?predicate ?object WHERE {\r\n" + "  SERVICE fts:textMatch {\r\n"
        + "    [] fts:query \"\\\"" + object + "\\\"\" ;\r\n" + "       fts:result ?object ;\r\n"
        + "  }\r\n" + "  ?subject ?predicate ?object .\r\n" + "  FILTER(STR(?object) = \"" + object
        + "\")\r\n" + "}\r\n" + "";
  }


}
