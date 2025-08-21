package BP;


public class Output {
  String subject;
  String predicate;
  String object;
  String dbName;
  String elapsedTime;

  public Output(String subject, String predicate, String object, String dbName,
      String elapsedTime) {
    this.subject = subject;
    this.predicate = predicate;
    this.object = object;
    this.elapsedTime = elapsedTime;
    this.dbName = dbName;
  }

  public String getSubject() {
    return subject;
  }

  public String getPredicate() {
    return predicate;
  }

  public String getObject() {
    return object;
  }

  public String getElapsedTime() {
    return elapsedTime;
  }

  public String getDbName() {
    return dbName;
  }
}
