package org.example.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JSONWMVariable {
    private Object _id;
    private Object name;
    private Object owner;
    private Object packageName;
    private Object tableName;
    private Object tableType;
    private Object liveSource;
    private Object maxResults;
    public JSONWMVariable() {
    }
    public JSONWMVariable(Object _id, Object name, Object owner, Object packageName, Object tableName, Object tableType, Object liveSource, Object maxResults) {
        this._id = _id;
        this.name = name;
        this.owner = owner;
        this.packageName = packageName;
        this.tableName = tableName;
        this.tableType = tableType;
        this.liveSource = liveSource;
        this.maxResults = maxResults;
    }
    public Object get_id() {
        return _id;
    }
    public void set_id(Object _id) {
        this._id = _id;
    }
    public Object getName() {
        return name;
    }
    public void setName(Object name) {
        this.name = name;
    }
    public Object getOwner() {
        return owner;
    }
    public void setOwner(Object owner) {
        this.owner = owner;
    }
    public Object getPackageName() {
        return packageName;
    }
    public void setPackageName(Object packageName) {
        this.packageName = packageName;
    }
    public Object getTableName() {
        return tableName;
    }
    public void setTableName(Object tableName) {
        this.tableName = tableName;
    }
    public Object getTableType() {
        return tableType;
    }
    public void setTableType(Object tableType) {
        this.tableType = tableType;
    }
    public Object getLiveSource() {
        return liveSource;
    }
    public void setLiveSource(Object liveSource) {
        this.liveSource = liveSource;
    }
    public Object getMaxResults() {
        return maxResults;
    }
    public void setMaxResults(Object maxResults) {
        this.maxResults = maxResults;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSONWMVariable that = (JSONWMVariable) o;
        return Objects.equals(_id, that._id) && Objects.equals(name, that.name) && Objects.equals(owner, that.owner) && Objects.equals(packageName, that.packageName) && Objects.equals(tableName, that.tableName) && Objects.equals(tableType, that.tableType) && Objects.equals(liveSource, that.liveSource) && Objects.equals(maxResults, that.maxResults);
    }
    @Override
    public int hashCode() {
        return Objects.hash(_id, name, owner, packageName, tableName, tableType, liveSource, maxResults);
    }
    @Override
    public String toString() {
        return "JSONWMVariable{" +
                "_id=" + _id +
                ", name=" + name +
                ", owner=" + owner +
                ", packageName=" + packageName +
                ", tableName=" + tableName +
                ", tableType=" + tableType +
                ", liveSource=" + liveSource +
                ", maxResults=" + maxResults +
                '}';
    }
}
