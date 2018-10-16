package com.common.util;

import com.google.openrtb.OpenRtb;
import com.google.openrtb.json.OpenRtbJsonFactory;
import com.google.openrtb.youdao.OpenRtbYDExtForDsp;
import yex.*;

import java.io.IOException;

/**
 * @Author yechao111987@126.com
 * @date 2018/10/16 10:18
 */
public class YexUtil {

    public static String writeResponse(OpenRtb.BidResponse bidResponse) {
        String res = "";
        try {
            res = yexOpenRtbJsonFactory.newWriter().writeBidResponse(bidResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return res;
        }
    }


    private static OpenRtbJsonFactory openRtbJsonFactory = OpenRtbJsonFactory.create()
            .register(new YDExtBattriReader(), OpenRtb.BidRequest.Imp.Native.Builder.class)
            .register(new YDExtAttriReader(), OpenRtb.BidResponse.SeatBid.Bid.Builder.class)
            .register(new YDExtStandardSchemaIdReader(), OpenRtb.BidRequest.Imp.Builder.class)
            .register(new YDExtStandardAssetReader<>(OpenRtbYDExtForDsp.sasset, Constants.EXTEND_STANDARD_ASSET_FIELD_NAME), OpenRtb.NativeRequest.Asset.Builder.class)
            .register(new YDExtBattriWriter(), Integer.class, OpenRtb.BidRequest.Imp.Native.class, Constants.EXTEND_BATTRI_FIELD_NAME)
            .register(new YDExtDataAssetTypeWriter(), Integer.class, OpenRtb.NativeRequest.Asset.Data.class, Constants.EXTEND_DATA_ASSET_TYPE_FIELD_NAME)
            .register(new YDExtStandardSchemaIdWriter(), Integer.class, OpenRtb.BidRequest.Imp.class, Constants.EXTEND_STANDARD_SCHEMA_ID_FIELD_NAME)
            .register(new YDExtStandardAssetWriter(), OpenRtb.NativeRequest.Asset.class, OpenRtb.NativeRequest.Asset.class, Constants.EXTEND_STANDARD_ASSET_FIELD_NAME);

    private static YexOpenRtbJsonFactory yexOpenRtbJsonFactory = YexOpenRtbJsonFactory.create()
            .yexRegister(new YDExtBattriReader(), OpenRtb.BidRequest.Imp.Native.Builder.class)
            .yexRegister(new YDExtAttriReader(), OpenRtb.BidResponse.SeatBid.Bid.Builder.class)
            .yexRegister(new YDExtStandardSchemaIdReader(), OpenRtb.BidRequest.Imp.Builder.class)
            .yexRegister(new YDExtStandardAssetReader<>(OpenRtbYDExtForDsp.sasset, Constants.EXTEND_STANDARD_ASSET_FIELD_NAME), OpenRtb.NativeRequest.Asset.Builder.class)
            .yexRegister(new YDExtBattriWriter(), Integer.class, OpenRtb.BidRequest.Imp.Native.class, Constants.EXTEND_BATTRI_FIELD_NAME)
            .yexRegister(new YDExtDataAssetTypeWriter(), Integer.class, OpenRtb.NativeRequest.Asset.Data.class, Constants.EXTEND_DATA_ASSET_TYPE_FIELD_NAME)
            .yexRegister(new YDExtStandardSchemaIdWriter(), Integer.class, OpenRtb.BidRequest.Imp.class, Constants.EXTEND_STANDARD_SCHEMA_ID_FIELD_NAME)
            .yexRegister(new YDExtStandardAssetWriter(), OpenRtb.NativeRequest.Asset.class, OpenRtb.NativeRequest.Asset.class, Constants.EXTEND_STANDARD_ASSET_FIELD_NAME);

}
