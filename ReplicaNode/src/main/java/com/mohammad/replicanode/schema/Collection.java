package com.mohammad.replicanode.schema;



import java.util.HashMap;

public class Collection implements SchemaOperation {
    private String collectionName;
    private HashMap<String, Document> documentGroup = new HashMap<>();

    public Collection(){}
    protected Collection(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public void add(String documentName) {
        Document document = new Document(documentName);
        documentGroup.put(documentName, document);

    }

    @Override
    public Document get(String documentName) {
        return documentGroup.get(documentName);
    }

    public HashMap<String, Document> getDocumentGroup() {
        return documentGroup;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setDocumentGroup(HashMap<String, Document> documentGroup) {
        this.documentGroup = documentGroup;
    }


    @Override
    public String toString() {
        return "Collection{" +
                collectionName + '\'' +
                "," + documentGroup +
                '}';
    }
}
