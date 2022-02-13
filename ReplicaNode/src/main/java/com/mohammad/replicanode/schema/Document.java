package com.mohammad.replicanode.schema;


import com.mohammad.replicanode.index.btree.BTree;
import com.mohammad.replicanode.schema.build.SchemaBuilder;
import static com.mohammad.replicanode.index.Index.createIndex;
import static com.mohammad.replicanode.utils.JSON.getJsonObject;
import static com.mohammad.replicanode.utils.JSON.isValidJson;

public class Document implements SchemaOperation {
    private String documentName;
    private BTree<String,String> documentSchema = new BTree<String,String>();

    public Document(){}

    protected Document(String documentName) {
        this.documentName = documentName;
    }

    public void add(String jsonObject,String indexProperty){
            documentSchema.put(indexProperty, SchemaBuilder.build(jsonObject, indexProperty));
        }

    @Override
    public void add(String jsonObject){
            documentSchema.put(getJsonObject(jsonObject,"_objectID"), jsonObject);
    }

    @Override
    public String get(String property){
        return documentSchema.get(property);
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public void setDocumentSchema(BTree<String, String> documentSchema) {
        this.documentSchema = documentSchema;
    }

    public String getDocumentName() {
        return documentName;
    }

    public BTree<String, String> getDocumentSchema() {
        return documentSchema;
    }

    @Override
    public String toString() {
        return documentSchema.toString();
    }
}

