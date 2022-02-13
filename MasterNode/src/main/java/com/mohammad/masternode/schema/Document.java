package com.mohammad.masternode.schema;

import com.mohammad.masternode.index.btree.BTree;
import com.mohammad.masternode.schema.build.SchemaBuilder;

import static com.mohammad.masternode.index.Index.createIndex;
import static com.mohammad.masternode.utils.JSON.getJsonObject;
import static com.mohammad.masternode.utils.JSON.isValidJson;

public class Document implements SchemaOperation {
    private String documentName;
    private BTree<String, String> documentSchema = new BTree<String, String>();

    public Document() {
    }

    public Document(String documentName) {
        this.documentName = documentName;
    }

    public void add(String jsonObject, String indexProperty) {
        if (isValidJson(jsonObject)) {
            documentSchema.put(indexProperty, SchemaBuilder.build(jsonObject, indexProperty));
        } else
            throw new IllegalArgumentException("the json entered invalid !!");
    }

    @Override
    public void add(String jsonObject) {
        if (isValidJson(jsonObject)) {
            documentSchema.put(SchemaBuilder.indexCounter(), SchemaBuilder.build(jsonObject));
        } else
            throw new IllegalArgumentException("the json entered invalid !!");
    }

    public void addExistJSON(String jsonObject) {
        documentSchema.put(getJsonObject(jsonObject, "_objectID"), jsonObject);
    }


    @Override
    public String get(String property) {
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

    @Override
    public String toString() {
        return documentSchema.toString();
    }
}

