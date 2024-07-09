package org.purbarun.cloudstream.model;

public record OrderMessage (OrderRequest orderRequest,String messageId) {
}
