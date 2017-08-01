db.system.js.save(
    {_id:"RegistrarProductos",
    "value":
        function(a,b){
            var k=0;
            for(var i=0;i<a.length;i++){
                db.ARTICULO.update(
                {_id:new ObjectId( a[i]["$oid"] )},
                {$inc:{stock:b[i]}}
                )
            }
        }
    }
)