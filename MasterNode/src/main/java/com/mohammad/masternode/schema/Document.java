package com.mohammad.masternode.schema;

import com.mohammad.masternode.index.btree.BTree;
import com.mohammad.masternode.schema.build.SchemaBuilder;

import static com.mohammad.masternode.index.Index.createIndex;
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
            indexProperty = createIndex(jsonObject, indexProperty);
            documentSchema.put(indexProperty, SchemaBuilder.build(jsonObject, indexProperty));
        }
        throw new IllegalArgumentException("the json entered invalid !!");
    }

    @Override
    public void add(String jsonObject) {
        if (isValidJson(jsonObject))
            documentSchema.put(SchemaBuilder.idCounter(), SchemaBuilder.build(jsonObject));
        throw new IllegalArgumentException("the json entered invalid !!");
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

    public BTree<String, String> getDocumentSchema() {
        return documentSchema;
    }

    @Override
    public String toString() {
        return documentSchema.toString();
    }
}

